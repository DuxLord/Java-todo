package net.gacame.practicar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.imageio.ImageIO;

public class ImageProcessingSystem {

    public static void main(String[] args) {
        // 1. Inicializar el sistema
        int numThreads = Runtime.getRuntime().availableProcessors(); // Número de hilos según núcleos disponibles
        System.out.println("Número de hilos disponibles: " + numThreads);
        ImageManager imageManager = new ImageManager(numThreads);

        // 2. Cargar imágenes
        imageManager.loadImages("ruta/a/imagenes/");

        // 3. Procesar imágenes con filtro Sepia
        imageManager.processImages(new SepiaFilter());

        // 4. Guardar imágenes procesadas
        imageManager.saveImages("ruta/a/imagenes/procesadas/");
    }

    // Interfaz para filtros de imagen
    interface ImageFilter {
        BufferedImage apply(BufferedImage image);
    }

    // Clase para aplicar el filtro Sepia
    static class SepiaFilter implements ImageFilter {
        @Override
        public BufferedImage apply(BufferedImage image) {
            // Lógica para aplicar el filtro Sepia a la imagen
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int pixel = image.getRGB(x, y);

                    // Separar los componentes del color
                    int alpha = (pixel >> 24) & 0xff;
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = pixel & 0xff;

                    // Aplicar el filtro sepia
                    int tr = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                    int tg = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                    int tb = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                    // Limitar los valores entre 0 y 255
                    red = Math.min(255, tr);
                    green = Math.min(255, tg);
                    blue = Math.min(255, tb);

                    // Establecer el nuevo color
                    pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                    image.setRGB(x, y, pixel);
                }
            }
            return image;
        }
    }

    // Clase para procesar imágenes en hilos separados
    static class ImageProcessor implements Runnable {
        private final BlockingQueue<BufferedImage> imageQueue;
        private final ImageFilter filter;

        public ImageProcessor(BlockingQueue<BufferedImage> imageQueue, ImageFilter filter) {
            this.imageQueue = imageQueue;
            this.filter = filter;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    BufferedImage image = imageQueue.take(); // Espera una imagen de la cola
                    BufferedImage processedImage = filter.apply(image); // Aplica el filtro
                    // Guardar o procesar la imagen procesada
                    ImageIO.write(processedImage, "jpg", new File("ruta/a/imagenes/procesadas/" + System.currentTimeMillis() + ".jpg"));
                } catch (InterruptedException | IOException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    // Clase para gestionar las imágenes y los hilos
    static class ImageManager {
        private final BlockingQueue<BufferedImage> imageQueue;
        private final Thread[] processorThreads;

        public ImageManager(int numThreads) {
            this.imageQueue = new LinkedBlockingQueue<>();
            this.processorThreads = new Thread[numThreads];
        }

        public void loadImages(String directoryPath) {
            File directory = new File(directoryPath);
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    try {
                        BufferedImage image = ImageIO.read(file);
                        imageQueue.put(image); // Agregar imagen a la cola
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Error al cargar la imagen: " + file.getName());
                    }
                }
            }
        }

        public void processImages(ImageFilter filter) {
            // Asignar el filtro al constructor del ImageProcessor
            for (int i = 0; i < processorThreads.length; i++) {
                processorThreads[i] = new Thread(new ImageProcessor(imageQueue, filter));
                processorThreads[i].start();
            }
        }

        public void saveImages(String directoryPath) {
            // Este método puede implementar lógica adicional para guardar las imágenes procesadas
            System.out.println("Imágenes guardadas en: " + directoryPath);
        }
    }
}
