/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.defs.state;

/**
 *
 * @author szymon
 */
public interface IOpponentCardStack {

    int decrease() throws WrongCardsCountInOpponentStackException;

    int getCount();

    int increase() throws WrongCardsCountInOpponentStackException;

    void setCount(int val) throws WrongCardsCountInOpponentStackException;

}
