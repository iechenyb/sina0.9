package com.cyb.thread.localthread;

public class ClientThread extends Thread {

    private SequenceA sequence;

    public ClientThread(SequenceA sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " => " + sequence.getNumber());
        }
    }
}