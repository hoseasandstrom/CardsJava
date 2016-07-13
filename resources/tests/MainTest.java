import com.studenttheironyard.Card;
import com.studenttheironyard.Card.Suit;
import com.studenttheironyard.Main;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by hoseasandstrom on 7/13/16.
 */
public class MainTest {

    public HashSet<Card> tempHash = new HashSet<>();

    @Test
    public  void aFlushTest() {

        Card card = new Card(Suit.SPADES, Card.Rank.EIGHT);
        Card card1 = new Card(Suit.SPADES, Card.Rank.NINE);
        Card card2 = new Card(Suit.SPADES, Card.Rank.QUEEN);
        Card card3 = new Card(Suit.SPADES, Card.Rank.SEVEN);

        tempHash.add(card);
        tempHash.add(card1);
        tempHash.add(card2);
        tempHash.add(card3);

        boolean testIsTrue = Main.isFlush(tempHash);
        Assert.assertTrue(testIsTrue);

        tempHash.remove(card1);
        tempHash.add(new Card(Suit.CLUBS, Card.Rank.EIGHT));
        boolean testIsfalse = Main.isFlush(tempHash);
        Assert.assertFalse(testIsfalse);
    }

    @Test
    public void bFourOfAKindTest(){
        Card card = new Card(Suit.SPADES, Card.Rank.EIGHT);
        Card card1 = new Card(Suit.HEARTS, Card.Rank.EIGHT);
        Card card2 = new Card(Suit.DIAMONDS, Card.Rank.EIGHT);
        Card card3 = new Card(Suit.CLUBS, Card.Rank.EIGHT);

        tempHash.add(card);
        tempHash.add(card1);
        tempHash.add(card2);
        tempHash.add(card3);

        boolean testIsTrue = Main.isSameRank(tempHash);
        Assert.assertTrue(testIsTrue);

        tempHash.remove(card1);
        tempHash.add(new Card(Suit.CLUBS, Card.Rank.NINE));
        testIsTrue = Main.isSameRank(tempHash);
        Assert.assertFalse(testIsTrue);
    }

    @Test
    public void cThreeOfKindTest (){
        Card card = new Card(Suit.SPADES, Card.Rank.SEVEN);
        Card card1 = new Card(Suit.HEARTS, Card.Rank.EIGHT);
        Card card2 = new Card(Suit.DIAMONDS, Card.Rank.EIGHT);
        Card card3 = new Card(Suit.CLUBS, Card.Rank.EIGHT);

        tempHash.add(card);
        tempHash.add(card1);
        tempHash.add(card2);
        tempHash.add(card3);

        boolean testIsTrue = Main.isThreeofKind(tempHash);
        Assert.assertTrue(testIsTrue);

        tempHash.remove(card1);
        tempHash.add(new Card(Suit.CLUBS, Card.Rank.NINE));
        testIsTrue = Main.isThreeofKind(tempHash);
        Assert.assertFalse(testIsTrue);


    }

    @Test
    public void dTwoPairTest (){
        Card card = new Card(Suit.SPADES, Card.Rank.SEVEN);
        Card card1 = new Card(Suit.HEARTS, Card.Rank.SEVEN);
        Card card2 = new Card(Suit.DIAMONDS, Card.Rank.EIGHT);
        Card card3 = new Card(Suit.CLUBS, Card.Rank.EIGHT);

        tempHash.add(card);
        tempHash.add(card1);
        tempHash.add(card2);
        tempHash.add(card3);

        boolean testIsTrue = Main.twoPair(tempHash);
        Assert.assertTrue(testIsTrue);

        tempHash.remove(card1);
        tempHash.add(new Card(Suit.CLUBS, Card.Rank.NINE));
        testIsTrue = Main.twoPair(tempHash);
        Assert.assertFalse(testIsTrue);


    }

    @Test
    public void eStraightTest(){
        Card card = new Card(Suit.SPADES, Card.Rank.SEVEN);
        Card card1 = new Card(Suit.HEARTS, Card.Rank.EIGHT);
        Card card2 = new Card(Suit.DIAMONDS, Card.Rank.SIX);
        Card card3 = new Card(Suit.CLUBS, Card.Rank.FIVE);

        tempHash.add(card);
        tempHash.add(card1);
        tempHash.add(card2);
        tempHash.add(card3);

        boolean testIsTrue = Main.straight(tempHash);
        Assert.assertTrue(testIsTrue);

        tempHash.remove(card1);
        tempHash.add(new Card(Suit.CLUBS, Card.Rank.NINE));
        testIsTrue = Main.straight(tempHash);
        Assert.assertFalse(testIsTrue);
    }
}
