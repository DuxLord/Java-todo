package net.gacame.MULTIHILO.ampl;

class BankAccountSynchronizedObject {
    private final Object lock = new Object(); // Objeto de sincronización / TAMBIEN SE PUEDE PONER "this" en lugar de lock
    private int balance;

    public BankAccountSynchronizedObject(int balance) {
        this.balance = balance;
    }

    public void addMoney(int amount) {
        synchronized (lock) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " agregó " + amount + "€. Saldo actual: " + balance + "€");
        }
    }

    public void takeOutMoney(int amount) {
        synchronized (lock) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " retiró " + amount + "€. Saldo actual: " + balance + "€");
            } else {
                System.out.println(Thread.currentThread().getName() + " intentó retirar " + amount + "€, pero el saldo es insuficiente.");
            }
        }
    }

    public int getBalance() {
        return balance;
    }
}

class BankThreadSave02 extends Thread {
    private BankAccountSynchronizedObject account;

    public BankThreadSave02(BankAccountSynchronizedObject account) {
        this.account = account;
        setName("Saver-" + getId());
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            account.addMoney(100);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class BankThreadSpend02 extends Thread {
    private BankAccountSynchronizedObject account;

    public BankThreadSpend02(BankAccountSynchronizedObject account) {
        this.account = account;
        setName("Spender-" + getId());
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            account.takeOutMoney(100);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Banco02 {
    public static void main(String[] args) {
        BankAccountSynchronizedObject account = new BankAccountSynchronizedObject(1000);

        BankThreadSave02[] savers = new BankThreadSave02[20];
        BankThreadSpend02[] spenders = new BankThreadSpend02[20];

        for (int i = 0; i < 20; i++) {
            savers[i] = new BankThreadSave02(account);
            spenders[i] = new BankThreadSpend02(account);
        }

        for (int i = 0; i < 20; i++) {
            savers[i].start();
            spenders[i].start();
        }

        try {
            for (int i = 0; i < 20; i++) {
                savers[i].join();
                spenders[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo final de la cuenta: " + account.getBalance() + "€");
    }
}