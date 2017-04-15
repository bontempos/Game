/*
  performing actions in a list and prints results to the console
 Press any key to start.
 actions in a list are automatically called by a Countdown object within 1 second.
 any trigger object can be defined for calling the next action in the list
 */

import bontempos.Game.Act.*;

ActionList actList = new ActionList();
int actionsPerformed = 0;

Act act;
boolean finished = false;

void setup() {
  size(100, 100);

  //initializes the Act object containing Actions, Countdowns, ActionLists and Checkers
  act = new Act(this);


  //creating a set of actions
  Action[] drawRectangle = {
    new Action("action01", this, "moveTo", 0, 0), 
    new Action("action02", this, "moveTo", width, 0), 
    new Action("action03", this, "moveTo", width, height)
  };

  //adding a set of actions into the ActionList object
  actList.add(drawRectangle);

  //if true, actions will be played in a loop
  actList.setAutoRepeat(false);  //optional

  //confirming actions were added on the ActionList object
  for (ActionList a : act.getActionLists() ) {
    for (int i = 0; i < actList.size(); i++) {
      Action e = a.get(i);
      println("Action", i, "name:", e.getActionName());
    }
  }
} 




void draw() {
  act.update();
}




void moveTo(float x, float y) {

  /*
   takes 1 second to execute;
   prepare an action to be performed after countdown = timeout
   start counting
   */

  println(millis(), "------> move to", x, y);

  Object target = this; 
  String methodName = "setFinished";
  boolean value = true;
  Action finished = new Action(target, methodName, value);


  Countdown c = new Countdown(1000, finished);
  c.start();
}




void setFinished(boolean finished) {
  println(millis(), "---- finished ", actionsPerformed, "------");
  this.finished = finished;

  //calls the next action on the actionList object
  Action.perform("evalNext");

  actionsPerformed++;
}




void keyTyped() {
  println(millis(), "------ START ----- ");

  //line below is only necessary if actionList object will perform actions in a loop
  actList.reset();

  //calls the next action on the actionList object
  Action.perform("evalNext");
}