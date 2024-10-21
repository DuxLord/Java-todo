package net.gacame.MULTIHILO;

//class BankAccountSynchronized {
//    private int balance;
//
//    public BankAccountSynchronized(int balance) {
//        this.balance = balance;
//    }
//
//    public synchronized void addMoney(int amount) {
//        balance += amount;
//        System.out.println(Thread.currentThread().getName() + " agregó " + amount + "€. Saldo actual: " + balance + "€");
//    }
//
//    public synchronized void takeOutMoney(int amount) {
//        balance -= amount;
//        System.out.println(Thread.currentThread().getName() + " retiró " + amount + "€. Saldo actual: " + balance + "€");
//
//    }
//
//    public int getBalance() {
//        return balance;
//    }
//}
//
//class BankThreadSave extends Thread {
//    private BankAccountSynchronized account;
//
//    public BankThreadSave(BankAccountSynchronized account) {
//        this.account = account;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i < 5; i++) {
//            account.addMoney(100);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
//
//class BankThreadSpend extends Thread {
//    private BankAccountSynchronized account;
//
//    public BankThreadSpend(BankAccountSynchronized account) {
//        this.account = account;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i < 5; i++) {
//            account.takeOutMoney(100);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        BankAccountSynchronized account = new BankAccountSynchronized(1000);
//
//        BankThreadSave[] savers = new BankThreadSave[20];
//        BankThreadSpend[] spenders = new BankThreadSpend[20];
//
//        for (int i = 0; i < 20; i++) {
//            savers[i] = new BankThreadSave(account);
//        }
//
//        for (int i = 0; i < 20; i++) {
//            spenders[i] = new BankThreadSpend(account);
//        }
//
//        for (int i = 0; i < 20; i++) {
//            savers[i].start();
//        }
//
//        for (int i = 0; i < 20; i++) {
//            spenders[i].start();
//        }
//
//        try {
//            for (int i = 0; i < 20; i++) {
//                savers[i].join();
//                spenders[i].join();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Saldo final de la cuenta: " + account.getBalance() + "€");
//    }
//}

class BankAccountSynchronized {
    private int balance;

    public BankAccountSynchronized(int balance) {
        this.balance = balance;
    }

    public synchronized void addMoney(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " agregó " + amount + "€. Saldo actual: " + balance + "€");
    }

    public synchronized void takeOutMoney(int amount) {
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + " retiró " + amount + "€. Saldo actual: " + balance + "€");

    }

    public int getBalance() {
        return balance;
    }
}

class BankThreadSave extends Thread {
    private BankAccountSynchronized account;

    public BankThreadSave(BankAccountSynchronized account) {
        this.account = account;
    }

    @Override
    public synchronized void run() {
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

class BankThreadSpend extends Thread {
    private BankAccountSynchronized account;

    public BankThreadSpend(BankAccountSynchronized account) {
        this.account = account;
    }

    @Override
    public synchronized void run() {
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

public class Main {
    public static void main(String[] args) {
        BankAccountSynchronized account = new BankAccountSynchronized(1000);

        BankThreadSave[] savers = new BankThreadSave[20];
        BankThreadSpend[] spenders = new BankThreadSpend[20];

        for (int i = 0; i < 20; i++) {
            savers[i] = new BankThreadSave(account);
        }

        for (int i = 0; i < 20; i++) {
            spenders[i] = new BankThreadSpend(account);
        }

        for (int i = 0; i < 20; i++) {
            savers[i].start();
        }

        for (int i = 0; i < 20; i++) {
            spenders[i].start();
        }

        System.out.println("Saldo final de la cuenta: " + account.getBalance() + "€");
    }
}
