package app.game;

import app.card.Ace;
import app.card.Card;
import app.card.Royal;
import app.dealer.Dealer;
import app.player.Player;
import org.junit.*;

import java.util.List;
import static org.junit.Assert.*;

public class GameTest {

    private static Dealer dealer;
    private static List<Player> players;
    private static Player player;
    private static Game game;

    @BeforeClass
    public static void oneTimeSetUp() throws Exception {

    }

    @AfterClass
    public static void oneTimeTearDown() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        game = new Game(new Player());
        dealer = game.dealer;
        players = game.players;
        player = players.get(0);
    }

    @After
    public void tearDown() throws Exception {
//        player.reset();
//        player.printWinnings();
//        dealer.reset();
//        dealer.resetDeck();
    }

    @Test
    public void addPlayer() {
    }

    @Test
    public void start() {
    }

    @Test
    public void checkWin() {
        dealer.addCard(new Royal("SPADES", 'Q'));
        dealer.addCard(new Card(7, "SPADES"));

        player.addCard(new Royal("SPADES", 'Q'));
        player.addCard(new Royal("SPADES", 'Q'));
        assertTrue(game.checkWin(0));
    }

    @Test
    public void checkPlayerHardAceWin() {
        dealer.addCard(new Royal("SPADES", 'Q'));
        dealer.addCard(new Card(7, "SPADES"));

        System.out.println("Adding player cards");

        player.addCard(new Card(7, "SPADES"));
        player.addCard(new Ace("SPADES"));
        player.addCard(new Ace("SPADES"));

        System.out.println(player.getCurrentHandValue());

        assertTrue(game.checkWin(0));
        assertFalse(player.isBusted());
    }


    @Test
    public void checkPlayerSoftAceWin() {
        dealer.addCard(new Royal("SPADES", 'Q'));
        dealer.addCard(new Card(7, "SPADES"));

        player.addCard(new Card(7, "SPADES"));
        player.addCard(new Ace("SPADES"));

        assertTrue(game.checkWin(0));
    }


    @Test
    public void checkPush() {
    }

    @Test
    public void bets() {
    }

    @Test
    public void validateBet() {
    }

    @Test
    public void turns() {
    }

    @Test
    public void results() {
    }

    @Test
    public void end() {
    }

    @Test
    public void printActivePlayerHand() {
    }

    @Test
    public void printTurn() {
    }

    @Test
    public void printPlaceBets() {
    }

    @Test
    public void printBetMessage() {
    }

    @Test
    public void welcomeMessage() {
    }

    @Test
    public void continueMessage() {
    }
}