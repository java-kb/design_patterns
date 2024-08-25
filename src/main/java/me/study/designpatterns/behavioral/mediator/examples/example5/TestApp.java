package me.study.designpatterns.behavioral.mediator.examples.example5;

import java.util.*;

public class TestApp {
    public static void main(String[] args) {
        FileSelectionDialog fileDialog = new FileSelectionDialog();
        Scanner scanner = new Scanner(System.in);
        int i;

        System.out.print("Exit[0], Filter[1], Dir[2], File[3], Selection[4]: ");
        i = scanner.nextInt();

        while (i != 0) {
            fileDialog.handleEvent(i - 1);
            System.out.print("Exit[0], Filter[1], Dir[2], File[3], Selection[4]: ");
            i = scanner.nextInt();
        }

        scanner.close();
    }
}


abstract class Widget {
    protected String name;
    private FileSelectionDialog mediator;

    public Widget(FileSelectionDialog mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public void changed() {
        mediator.widgetChanged(this);
    }

    public abstract void updateWidget();
    public abstract void queryWidget();
}

class List extends Widget {
    public List(FileSelectionDialog dir, String name) {
        super(dir, name);
    }

    @Override
    public void queryWidget() {
        System.out.println("   " + name + " list queried");
    }

    @Override
    public void updateWidget() {
        System.out.println("   " + name + " list updated");
    }
}

class Edit extends Widget {
    public Edit(FileSelectionDialog dir, String name) {
        super(dir, name);
    }

    @Override
    public void queryWidget() {
        System.out.println("   " + name + " edit queried");
    }

    @Override
    public void updateWidget() {
        System.out.println("   " + name + " edit updated");
    }
}

class FileSelectionDialog {
    public enum Widgets {
        FilterEdit, DirList, FileList, SelectionEdit
    }

    private Widget[] components;

    public FileSelectionDialog() {
        components = new Widget[4];
        components[Widgets.FilterEdit.ordinal()] = new Edit(this, "filter");
        components[Widgets.DirList.ordinal()] = new List(this, "dir");
        components[Widgets.FileList.ordinal()] = new List(this, "file");
        components[Widgets.SelectionEdit.ordinal()] = new Edit(this, "selection");
    }

    public void handleEvent(int which) {
        components[which].changed();
    }

    public void widgetChanged(Widget theChangedWidget) {
        if (theChangedWidget == components[Widgets.FilterEdit.ordinal()]) {
            components[Widgets.FilterEdit.ordinal()].queryWidget();
            components[Widgets.DirList.ordinal()].updateWidget();
            components[Widgets.FileList.ordinal()].updateWidget();
            components[Widgets.SelectionEdit.ordinal()].updateWidget();
        } else if (theChangedWidget == components[Widgets.DirList.ordinal()]) {
            components[Widgets.DirList.ordinal()].queryWidget();
            components[Widgets.FileList.ordinal()].updateWidget();
            components[Widgets.FilterEdit.ordinal()].updateWidget();
            components[Widgets.SelectionEdit.ordinal()].updateWidget();
        } else if (theChangedWidget == components[Widgets.FileList.ordinal()]) {
            components[Widgets.FileList.ordinal()].queryWidget();
            components[Widgets.SelectionEdit.ordinal()].updateWidget();
        } else if (theChangedWidget == components[Widgets.SelectionEdit.ordinal()]) {
            components[Widgets.SelectionEdit.ordinal()].queryWidget();
            System.out.println("   file opened");
        }
    }
}




