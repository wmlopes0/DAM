package modeloProductorConsumidor;

public class Pasteleria {

    private int bizcochos;

    public Pasteleria(int bizcochos) {
        this.bizcochos = bizcochos;
    }

    public synchronized void producir(int numBizcochos) {
        while (bizcochos != 0) {
            try {
                wait();//Si hay bizcochos espero
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        bizcochos = numBizcochos;
        System.out.println("El pastelero ha producido " + bizcochos + " bizcochos.");
        notifyAll();//Notifico
    }

    public synchronized void consumir(Consumidor consumidor) {
        while (bizcochos == 0) {
            try {
                wait();//Espero
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
        bizcochos--;
        System.out.println(consumidor.getNombre() + " ha consumido 1 bizcocho, quedan " + bizcochos + " bizcochos.");
        notifyAll();
    }

}
