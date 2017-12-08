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
float[] currentPosition = {0f, 0f};

void setup() {
  size(100, 100);

  //initializes the Act object containing Actions, Countdowns, ActionLists and Checkers
  act = new Act(this);


  //creating a set of actions
  Action[] drawRectangle = {
    new Action("action01", this, "moveTo", 10, 10), 
    new Action("action02", this, "moveToDraw", width-10, 10), 
    new Action("action03", this, "moveToDraw", width-10, height-10)
  };

  //adding a set of actions into the ActionList object
  actList.addSet(drawRectangle);

  //if true, actions will be played in a loop
  actList.setAutoRepeat(false);  //optional

  //confirming actions were added on the ActionList object
  println("listed actions to execute");
  actList.printList();
} 




void draw() {
}


void moveToDraw(float x, float y) {
  line( currentPosition[0], currentPosition[1], x, y );
  moveTo(x, y);
}

void moveTo(float x, float y) {

  /*
   this takes 1 second to execute;
   prepare an action to be performed after countdown = timeout
   start counting
   */

  println(millis(), "------> move to", x, y);

  String currentActionWasInvokedby = actList.getCurrentEvalActionName();
  println("##:", currentActionWasInvokedby);

  //next 3 lines are optional to fill Action parameters below
  //Object target = this; 
  //String methodName = "setFinished";
  //boolean value = true;

  //Action finished = new Action(target, methodName, value); //use this line if the action "finished" will be used again in another place.

  //Countdown c = new Countdown(1000, finished); //countdown = timer

  //the line below is the shortcut for adding a timer constrained to all lines commented above.

  Countdown c = new Countdown(1000, new Action(this, "setFinished", true)); //TODO i want this name to be dynamically set  based on the action which initially invoked this method

  currentPosition[0] = x;
  currentPosition[1] = y;

  c.start();
}




void setFinished(boolean finished) {
  println(millis(), "---- finished ", actionsPerformed, "------");
  this.finished = finished;

  //calls the next action on the actionList object
  //next line creates an action called "evalNext" <- default name for the actionList listner/checker.
  Action.perform("evalNext");  

  actionsPerformed++;
}




void keyTyped() {
  println(millis(), "------ START ----- ");

  background(random(200, 255));
  //line below is only necessary if actionList object will perform actions in a loop
  actList.reset();

  //calls the next action on the actionList object
  Action.perform("evalNext");
}