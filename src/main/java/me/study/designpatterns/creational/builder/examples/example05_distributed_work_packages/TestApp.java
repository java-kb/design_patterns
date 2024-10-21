package me.study.designpatterns.creational.builder.examples.example05_distributed_work_packages;

import java.io.*;

public class TestApp {
    private static final int NUM_ENTRIES = 6;

    public static void main(String[] args) {
        PersistenceAttribute[] input = new PersistenceAttribute[NUM_ENTRIES];
        input[0] = new PersistenceAttribute();
        input[0].type = PersistenceType.File;
        input[0].value = "state.dat";
        input[1] = new PersistenceAttribute();
        input[1].type = PersistenceType.File;
        input[1].value = "config.sys";
        input[2] = new PersistenceAttribute();
        input[2].type = PersistenceType.Queue;
        input[2].value = "compute";
        input[3] = new PersistenceAttribute();
        input[3].type = PersistenceType.Queue;
        input[3].value = "log";
        input[4] = new PersistenceAttribute();
        input[4].type = PersistenceType.Pathway;
        input[4].value = "authentication";
        input[5] = new PersistenceAttribute();
        input[5].type = PersistenceType.Pathway;
        input[5].value = "error processing";

        UnixBuilder unixBuilder = new UnixBuilder();
        VmsBuilder vmsBuilder = new VmsBuilder();
        Reader reader = new Reader();

        reader.setBuilder(unixBuilder);
        reader.construct(input, NUM_ENTRIES);
        System.out.println(unixBuilder.getResult().getState());

        reader.setBuilder(vmsBuilder);
        reader.construct(input, NUM_ENTRIES);
        System.out.println(vmsBuilder.getResult().getState());
    }
}
enum PersistenceType {
    File, Queue, Pathway
}

class PersistenceAttribute {
    PersistenceType type;
    String value;
}

class DistrWorkPackage {
    private String _desc;
    private String _temp;

    public DistrWorkPackage(String type) {
        _desc = "Distributed Work Package for: " + type;
    }

    public void setFile(String f, String v) {
        _temp = "\n  File(" + f + "): " + v;
        _desc += _temp;
    }

    public void setQueue(String q, String v) {
        _temp = "\n  Queue(" + q + "): " + v;
        _desc += _temp;
    }

    public void setPathway(String p, String v) {
        _temp = "\n  Pathway(" + p + "): " + v;
        _desc += _temp;
    }

    public String getState() {
        return _desc;
    }
}

abstract class Builder {
    protected DistrWorkPackage _result;

    public abstract void configureFile(String name);
    public abstract void configureQueue(String queue);
    public abstract void configurePathway(String type);
    public DistrWorkPackage getResult() {
        return _result;
    }
}

class UnixBuilder extends Builder {
    public UnixBuilder() {
        _result = new DistrWorkPackage("Unix");
    }

    public void configureFile(String name) {
        _result.setFile("flatFile", name);
    }

    public void configureQueue(String queue) {
        _result.setQueue("FIFO", queue);
    }

    public void configurePathway(String type) {
        _result.setPathway("thread", type);
    }
}

class VmsBuilder extends Builder {
    public VmsBuilder() {
        _result = new DistrWorkPackage("Vms");
    }

    public void configureFile(String name) {
        _result.setFile("ISAM", name);
    }

    public void configureQueue(String queue) {
        _result.setQueue("priority", queue);
    }

    public void configurePathway(String type) {
        _result.setPathway("LWP", type);
    }
}

class Reader {
    private Builder _builder;

    public void setBuilder(Builder b) {
        _builder = b;
    }

    public void construct(PersistenceAttribute[] list, int num) {
        for (int i = 0; i < num; i++) {
            if (list[i].type == PersistenceType.File) {
                _builder.configureFile(list[i].value);
            } else if (list[i].type == PersistenceType.Queue) {
                _builder.configureQueue(list[i].value);
            } else if (list[i].type == PersistenceType.Pathway) {
                _builder.configurePathway(list[i].value);
            }
        }
    }
}

