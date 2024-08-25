package me.study.designpatterns.behavioral.templatemethod.examples.example4_gameai;

public class MonstersAI extends GameAI {
    @Override
    protected void collectResources() {
        System.out.println("MonstersAI: collectResources");
        for (Structure s : this.builtStructures) {
            s.collect();
        }
    }
    @Override
    protected void buildStructures() {
        System.out.println("MonstersAI: buildStructures");
    }

    @Override
    protected void buildUnits() {
        System.out.println("MonstersAI: buildStructures");
        // if (there are plenty of resources) then
        // if (there are no scouts)
        //     // Build peon, add it to scouts group.
        // else
        //     // Build grunt, add it to warriors group.
        // TODO Auto-generated method stub
        
    }
}
