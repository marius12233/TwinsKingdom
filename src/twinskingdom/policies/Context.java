/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.policies;

/**
 *
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
