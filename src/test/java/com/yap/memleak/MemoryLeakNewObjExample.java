package com.yap.memleak;

public class MemoryLeakNewObjExample {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new BugObject();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static class BugObject {
        private byte[] buff = new byte[10 * 1024 * 1024]; // 10m
        public BugObject() {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> BugObject.this.destroy()));
        }
        public void destroy() {
            System.out.println("destroy");
        }
    }


}
