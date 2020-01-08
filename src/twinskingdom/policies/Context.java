/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.policies;

/**
 * The Context class manages the current strategy to maintain.
 * It is used by a client, which is the Enemy, and uses Strategy class.
 *
 */
public class Context {
    
    private BasePolicy strategy=null;
    
    
    public BasePolicy getStrategy() {
        return strategy;
    }

    public void setStrategy(BasePolicy strategy) {
        this.strategy = strategy;
    }
    
    public void getMovement(){
        strategy.getMovement();
    }

}
