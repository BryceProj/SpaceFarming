package Abilities;

import Animations.SuperAnimation;

public class SuperAbility {

    public String name;
    public String[] status;
    //some animation class
    public SuperAnimation[] animation;

    //what ability does here

    public SuperAbility() {}

    public SuperAbility(String name, String[] status, SuperAnimation[] animation) {
        this.name = name;
        this.status = status;
        this.animation = animation;
    }

}
