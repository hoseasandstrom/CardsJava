package com.studenttheironyard;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {

    static HashSet<Card> createDeck() {
        HashSet<Card> deck = new HashSet<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card c = new Card(suit, rank);
                deck.add(c);
            }
        }
        return deck;
    }

    static HashSet<HashSet<Card>> createHands(HashSet<Card> deck) {
        HashSet<HashSet<Card>> hands = new HashSet<>();
        for (Card c1 : deck) {
            HashSet<Card> deck2 = (HashSet<Card>) deck.clone();
            deck2.remove(c1);
            for (Card c2 : deck2) {
                HashSet<Card> deck3 = (HashSet<Card>) deck2.clone();
                deck3.remove(c2);
                for (Card c3 : deck3) {
                    HashSet<Card> deck4 = (HashSet<Card>) deck3.clone();
                    deck4.remove(c3);
                    for (Card c4 : deck4) {
                        HashSet<Card> hand = new HashSet<>();
                        hand.add(c1);
                        hand.add(c2);
                        hand.add(c3);
                        hand.add(c4);
                        hands.add(hand);
                    }
                }
            }
        }
        return hands;
    }

    public static boolean isFlush(HashSet<Card> hand) {
        HashSet<Card.Suit> suits = hand.stream()
                .map(card -> card.suit)
                .collect(Collectors.toCollection(HashSet<Card.Suit>::new));
        return suits.size() == 1;
    }

    static boolean isFourOfAKind(HashSet<Card> hand) {
        HashSet<Card.Rank> ranks = hand.stream()
                .map(card -> card.rank)
                .collect(Collectors.toCollection(HashSet<Card.Rank>::new));
        return ranks.size() == 1;
    }

    public static boolean isSameRank(HashSet<Card> hand){
        HashSet<Card.Rank> ranks = hand.stream()
                .map (card -> card.rank)
                .collect(Collectors.toCollection(HashSet<Card.Rank>::new));
        return ranks.size() == 1;
    }

    public static boolean isThreeofKind(HashSet<Card> hand){
        ArrayList<Integer> ordinalarray = hand.stream()
                .map (card -> card.rank.ordinal())
                .collect(Collectors.toCollection(ArrayList<Integer>::new));

        int [] freqList = new int[13];

        for (Integer i: ordinalarray) {
            freqList[i] = freqList[i]+ 1;
        }

        for (int i: freqList) {
            if (i ==3){
                return true;
            }
        }
        return false;
    }

    public static boolean twoPair(HashSet<Card> hand){
        ArrayList<Integer> ordinalarray = hand.stream()
                .map (card -> card.rank.ordinal())
                .collect(Collectors.toCollection(ArrayList<Integer>::new));

        int [] freqList = new int[13];
        int pair = 0;

        for (Integer i: ordinalarray) {
            freqList[i] = freqList[i]+ 1;
        }
        for (int i: freqList) {
            if (i ==2){
                pair++;
            }
        }
        if (pair==2){
            return true;
        }
        return false;
    }

    public static boolean straight(HashSet<Card> hand){
        ArrayList<Integer> ordinalarray = hand.stream()
                .map (card -> card.rank.ordinal())
                .collect(Collectors.toCollection(ArrayList<Integer>::new));

        ArrayList<Integer> sortedArray = ordinalarray.stream()
                .sorted((x1, x2) -> Integer.compare(x1.intValue(),x2.intValue()))
                .collect(Collectors.toCollection(ArrayList<Integer>::new));

        if (sortedArray.get(0) + 1 == sortedArray.get(1) && sortedArray.get(0) + 2 ==sortedArray.get(2) && sortedArray.get(0) + 3 ==sortedArray.get(3)){
            return true;
        }
        return false;
    }

    public static boolean straightFlush(HashSet<Card> hand){
        if (isFlush(hand) && straight(hand)){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        HashSet<Card> deck = createDeck();
        HashSet<HashSet<Card>> hands = createHands(deck);

        HashSet<HashSet<Card>>flushes = hands.stream()
                .filter(Main::isFlush)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));


        HashSet<HashSet<Card>>fourOfAKind = hands.stream()
                .filter(Main::isSameRank)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        HashSet<HashSet<Card>>threeOfAKind = hands.stream()
                .filter(Main::isThreeofKind)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        HashSet<HashSet<Card>>twoPairs = hands.stream()
                .filter(Main::twoPair)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        HashSet<HashSet<Card>>straight = hands.stream()
                .filter(Main::straight)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        HashSet<HashSet<Card>>straightFlush = hands.stream()
                .filter(Main::straightFlush)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        System.out.println("Possible outcomes for a deck of cards - 4 card hands:");
        System.out.println("");
        System.out.println("Total number of hands: " + hands.size());
        System.out.println("Total number of two-pairs: " + twoPairs.size());
        System.out.println("Total number of three-of-a-kinds: " + threeOfAKind.size());
        System.out.println("Total number of four-of-a-kinds: " + fourOfAKind.size());
        System.out.println("Total number of straights: " + straight.size());
        System.out.println("Total number of flushes: " + flushes.size());
        System.out.println("Total number of straight flushes: " + straightFlush.size());
        System.out.println("");
        System.out.println("So much math...");
    }
}
