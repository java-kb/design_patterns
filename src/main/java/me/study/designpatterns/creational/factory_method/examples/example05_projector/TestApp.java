package me.study.designpatterns.creational.factory_method.examples.example05_projector;

public class TestApp {

    public static void main(String[] args) {
        String info = "Very important info of the presentation";

        ClientCode clientCode = new ClientCode();

        // Present info over WiFi
        clientCode.present(info, new WifiFactory());

        // Present info over Bluetooth
        clientCode.present(info, new BluetoothFactory());
    }
}

interface ProjectorFactory {

    Projector createProjector();

    default Projector syncedProjector(Projector projector) {
        Projector newProjector = createProjector();
        newProjector.sync(projector);
        return newProjector;
    }
}

class WifiFactory implements ProjectorFactory {

    @Override
    public Projector createProjector() {
        return new WifiProjector();
    }
}

class BluetoothFactory implements ProjectorFactory {

    @Override
    public Projector createProjector() {
        return new BluetoothProjector();
    }
}

interface Projector {

    int getCurrentPage();

    void present(String info);

    void sync(Projector projector);

    void update(int page);
}

class WifiProjector implements Projector {

    private int currentPage = 0;

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public void present(String info) {
        System.out.println("Info is presented over Wifi: " + info);
    }

    @Override
    public void update(int page) {
        // ... scroll page via WiFi connection
        // ...
        currentPage = page;
    }

    @Override
    public void sync(Projector projector) {
        projector.update(currentPage);
    }
}

class BluetoothProjector implements Projector {

    private int currentPage = 0;

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public void present(String info) {
        System.out.println("Info is presented over Bluetooth: " + info);
    }

    @Override
    public void update(int page) {
        // ... scroll page via Bluetooth connection
        // ...
        currentPage = page;
    }

    @Override
    public void sync(Projector projector) {
        projector.update(currentPage);
    }
}

class ClientCode {

    private Projector currentProjector;

    public void present(String info, ProjectorFactory factory) {
        if (currentProjector == null) {
            Projector projector = factory.createProjector();
            projector.present(info);
            currentProjector = projector;
        } else {
            currentProjector = factory.syncedProjector(currentProjector);
            currentProjector.present(info);
        }
    }
}