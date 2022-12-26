package app.game;

import app.exception.HandException;
import app.hand.Hand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ActionSingleton {

    private static ActionSingleton instance = null;

    public Map<Integer, HashMap<String, Object>> actionMap;

    private ActionSingleton() {
        this.actionMap = new HashMap<>();
        actionMap.put(0, new HashMap<>());
        Map hitOrStand = actionMap.get(0);
        hitOrStand.put("actions", new String[] {"h", "st"});
        hitOrStand.put("message", "Please hit(h) or stand(st)");
        actionMap.put(1, new HashMap<>());
        Map hitStandOrDouble = actionMap.get(1);
        hitStandOrDouble.put("actions", new String[] {"h", "st", "d"});
        hitStandOrDouble.put("message", "Please hit(h), stand(st), or double(d)");
        actionMap.put(2, new HashMap<>());
        Map hitStandDoubleOrSplit = actionMap.get(2);
        hitStandDoubleOrSplit.put("actions", new String[] {"h", "st", "d", "sp"});
        hitStandDoubleOrSplit.put("message", "Please hit(h), stand(st), double(d), or split(sp)");
    }

    public static ActionSingleton getInstance() {
        if (instance == null) {
            instance = new ActionSingleton();
        }
        return instance;
    }

    public String[] getActions(int actionLevel) {
        return (String[]) actionMap.get(actionLevel).get("actions");
    }

    public String getMessage(int actionLevel) {
        return (String) actionMap.get(actionLevel).get("message");
    }

    public boolean checkInput(String playerInput, Hand hand) throws HandException {
        boolean result = true;
        if(Arrays.asList(getActions(getActionSet(hand))).contains(playerInput.toLowerCase()))
            result = false;


        return result;
    }

    public int getActionSet(Hand hand) throws HandException {
        int actionLevel = 0;

        if(hand.numCards() == 2) {
            actionLevel++;
            if(hand.canSplit()) actionLevel++;
        }

        return actionLevel;
    }
}
