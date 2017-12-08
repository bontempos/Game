/*
  create Countdown timer objects and perform actions when timeout = true
 */

import bontempos.Game.Act.*;

Act act;


CustomClass timeKeeper = new CustomClass();

Countdown t1 = new Countdown(1000);

Action customAction = new Action(this, "doSomething");
Countdown t2 = new Countdown(2000, customAction);

Action trackSeconds = new Action(timeKeeper, "newSecond");
Countdown t3 = new Countdown(1000, trackSeconds);

Countdown tt = new Countdown(1500, new Action("sayIt") );

//Checker ckAction = new Checker("actionFinished");
Checker ckAction, hearIt;

Action actionFinished = new Action(this, "setValue", true);

int secondCount;

void setup() {
  size(100, 100);
  
  //initializing Act Object so we can gain access to Countdown and Action objects;
  act = new Act(this);
  
  //ckAction = new Checker(actionFinished);
  //actionFinished.setName("actionFinished");
  
  ckAction = new Checker("actionFinished", this, "setValue", true);
  hearIt = new Checker("sayIt", this, "heard");
  println(millis(), "t starts now");
  t1.start();
  t3.setRepeat(true);
  t3.start();
  tt.start();
}

void draw() {


  if (t1.timeout()) {
    t1.reset();
    println(millis(), "t2.starts now");
    t2.start();
  }

  if (t2.timeout()) {
    t2.reset();
    println(millis(), "im done");
    //actionFinished.eval();
    Action.perform("actionFinished");
    //ckAction.remove();
  }
}


void doSomething() {
  println(millis(), "I am doing it");
}


void setValue(boolean b){
  println("value was set to", b);
}

void heard(){
  println(millis(), "I have heard");
}

public class CustomClass { //set explicit as public or Action object can't perform here
  CustomClass() {
  }
  void newSecond() {
    secondCount++;
    println("-->", secondCount);
  }
}