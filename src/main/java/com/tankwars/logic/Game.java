package com.tankwars.logic;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<GameRow> rows = new ArrayList<>();
    private int userTankPositionX = 12;
    private int userTankPositionY = 27;
    private boolean tanksTurn = true;
    private int pointsUser = 0;
    private int pointsBoot = 0;
    private boolean bootTankExist = true;
    private boolean userTankExist = true;
    private GridPane gridPane;
    private Label label;

    public void addPoint(GameElement gameElement) {
        if (gameElement instanceof UserTankElement) {
            pointsUser++;
        } else if (gameElement instanceof BootElement) {
            pointsBoot++;
        }
    }

    public void checkTanks() {
        if (!bootTankExist) {
            placeBootTank();
        } else if (!userTankExist) {
            placeUserTank();
        }
    }

    public boolean endOfGame() {
        if ((pointsUser >= 5) || (pointsBoot >= 5))
            return true;
        else
            return false;
    }

    public String checkPoints() {
        String result = "";
        if (pointsUser >= 5) {
            result = "You WIN :)";
        } else if (pointsBoot >= 5) {
            result = "You LOSE :(";
        }
        return result;
    }

    public int getPointsUser() {
        return pointsUser;
    }

    public int getPointsBoot() {
        return pointsBoot;
    }

    public boolean isTanksTurn() {
        return tanksTurn;
    }

    public void switchTurn() {
        if (tanksTurn) {
            tanksTurn = false;
        } else {
            tanksTurn = true;
        }
    }

    public Game(GridPane gridPane, Label label) {
        this.gridPane = gridPane;
        this.label = label;
        for (int i = 0; i < 29; i++) {
            rows.add(new GameRow());
        }
        initBoard();
    }

    public GameElement getElement(int x, int y) {
        return rows.get(y).getCols().get(x);
    }

    public void setElement(int x, int y, GameElement gameElement) {
        rows.get(y).getCols().set(x, gameElement);
    }

    public void findElementAndChangeItsDirection(KeyCode code) {
        for (int x = 1; x < 29; x++) {
            for (int y = 1; y < 29; y++) {
                GameElement element = getElement(x, y);
                if (element instanceof UserTankElement) {
                    ((UserTankElement) element).setDirection(code);
                }
            }
        }
    }

    public void fireBullet() {
        for (int x = 1; x < 29; x++) {
            for (int y = 1; y < 29; y++) {
                GameElement element = getElement(x, y);
                if (element instanceof UserTankElement) {
                    placeAndFireBullet(x, y, element);
                }
            }
        }
    }

    private void placeAndFireBullet(int x, int y, GameElement element) {
        int xDirection = 0;
        int yDirection = 0;
        switch (element.getDirection()) {
            case EAST:
                xDirection = (-1);
                break;
            case WEST:
                xDirection = (1);
                break;
            case NORTH:
                yDirection = (-1);
                break;
            case SOUTH:
                yDirection = (1);
                break;
        }
        checkAndPlaceBullet(x, y, element.getDirection(), xDirection, yDirection);
    }

    public void fireBulletBoot() {
        for (int x = 1; x < 29; x++) {
            for (int y = 1; y < 29; y++) {
                GameElement element = getElement(x, y);
                if (element instanceof BootElement) {
                    placeAndFireBullet(x, y, element);
                }
            }
        }
    }

    private void checkAndPlaceBullet(int x, int y, TankDirection tankDirection, int xDirection, int yDirection) {
        if (getElement((x + xDirection), (y + yDirection)) instanceof NoneElement) {
            placeNewBullet(x, y, tankDirection, xDirection, yDirection);
        } else checkAndHitTank(x, y, xDirection, yDirection);
    }

    private void hitUserTank(int x, int y, int xDirection, int yDirection) {
        addPoint(new BootElement());
        placeUserTank();
        setElement((x + xDirection), (y + yDirection), new NoneElement());
    }

    private void hitBootTank(int x, int y, int xDirection, int yDirection) {
        addPoint(new UserTankElement());
        placeBootTank();
        setElement((x + xDirection), (y + yDirection), new NoneElement());
    }

    private void placeNewBullet(int x, int y, TankDirection tankDirection, int xDirection, int yDirection) {
        setElement((x + xDirection), (y + yDirection), new BulletElement(tankDirection));
    }

    public void findBootAndChangeItsDirection() {
        if (isTanksTurn()) {
            for (int x = 1; x < 29; x++) {
                for (int y = 1; y < 29; y++) {
                    GameElement element = getElement(x, y);
                    if (element instanceof BootElement) {
                        ((BootElement) element).setRandomDirection();
                    }
                }
            }
        }
    }

    public void initBoard() {
        createBoardFrame();
        placeTanks();

        for (int i = 0; i < 15; i++) {
            placeWallHorizontal();
        }
        for (int i = 0; i < 15; i++) {
            placeWallVertical();
        }
        for (int i = 0; i < 50; i++) {
            placeWallElementInRandomEmptyPosition();
        }
    }

    private void createBoardFrame() {
        for (int x = 0; x < 29; x++) {
            setElement(x, 0, new WallElement());
            setElement(x, 28, new WallElement());
        }
        for (int y = 0; y < 29; y++) {
            setElement(0, y, new WallElement());
            setElement(28, y, new WallElement());
        }
    }

    private void placeTanks() {
        setElement(userTankPositionX, userTankPositionY, new UserTankElement());
        setElement(12, 1, new BootElement());
        setElement(1, 12, new BootElement());
        setElement(27, 12, new BootElement());
    }

    public void placeUserTank() {
        if (getElement(userTankPositionX, userTankPositionY) instanceof NoneElement) {
            setElement(userTankPositionX, userTankPositionY, new UserTankElement());
            userTankExist = true;
        } else {
            userTankExist = false;
        }
    }

    public void placeBootTank() {
        if (getElement(12, 1) instanceof NoneElement) {
            setElement(12, 1, new BootElement());
            bootTankExist = true;
        } else {
            bootTankExist = false;
        }
    }

    public void placeWallHorizontal() {
        int x = getRandomNumberInRange();
        int y = getRandomNumberInRange();

        while (!(getElement(x, y) instanceof NoneElement)) {
            x = randomNumberInRange(2, 26);
            y = randomNumberInRange(2, 26);
        }
        setElement(x, y, new WallElement());

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < randomNumber(9); j++) {
                if (x + j < 27) {
                    if (getElement(x + j, y) instanceof NoneElement) {
                        setElement(x + j, y, new WallElement());
                    }
                }
            }
        }
    }

    public void placeWallVertical() {
        int x = getRandomNumberInRange();
        int y = getRandomNumberInRange();

        while (!(getElement(x, y) instanceof NoneElement)) {
            x = randomNumberInRange(2, 26);
            y = randomNumberInRange(2, 26);
        }
        setElement(x, y, new WallElement());

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < randomNumber(9); j++) {
                if (y + j < 27) {
                    if (getElement(x, y + j) instanceof NoneElement) {
                        setElement(x, y + j, new WallElement());
                    }
                }
            }
        }
    }

    public static int randomNumber(int i) {
        return (int) (Math.random() * (i + 1));
    }

    public void placeWallElementInRandomEmptyPosition() {
        int x = getRandomNumberInRange();
        int y = getRandomNumberInRange();

        while (!(getElement(x, y) instanceof NoneElement)) {
            x = getRandomNumberInRange();
            y = getRandomNumberInRange();
        }
        setElement(x, y, new WallElement());
    }

    public int getRandomNumberInRange() {
        return randomNumberInRange(2, 26);
    }

    public static int randomNumberInRange(int x, int y) {
        int min = x;
        int max = y;
        if (x > y) {
            min = y;
            max = x;
        }
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public void moveBullet() {
        for (int x = 1; x < 28; x++) {
            for (int y = 1; y < 28; y++) {
                GameElement element = getElement(x, y);
                if (element instanceof BulletElement) {
                    int xDirection = 0;
                    int yDirection = 0;
                    switch (element.getDirection()) {
                        case EAST:
                            xDirection = (-1);
                            checkAndMoveBulletOneStep(x, y, element, xDirection, yDirection);
                            break;
                        case WEST:
                            xDirection = (1);
                            checkAndMoveBulletOneStep(x, y, element, xDirection, yDirection);
                            x++;
                            break;
                        case NORTH:
                            yDirection = (-1);
                            checkAndMoveBulletOneStep(x, y, element, xDirection, yDirection);
                            break;
                        case SOUTH:
                            yDirection = (1);
                            checkAndMoveBulletOneStep(x, y, element, xDirection, yDirection);
                            y++;
                            break;
                    }
                }
            }
        }
    }

    public void moveTanks() {
        if (isTanksTurn()) {
            for (int x = 1; x < 28; x++) {
                for (int y = 1; y < 28; y++) {
                    GameElement element = getElement(x, y);
                    if (element instanceof BootElement || element instanceof UserTankElement) {
                        int xDirection = 0;
                        int yDirection = 0;
                        switch (element.getDirection()) {
                            case EAST:
                                xDirection = (-1);
                                checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                                break;
                            case WEST:
                                xDirection = (1);
                                checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                                x++;
                                break;
                            case NORTH:
                                yDirection = (-1);
                                checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                                break;
                            case SOUTH:
                                yDirection = (1);
                                checkAndMoveElementOneStep(x, y, element, xDirection, yDirection);
                                y++;
                                break;
                        }
                    }
                }
            }
        }
    }

    public void checkAndMoveElementOneStep(int x, int y, GameElement element, int xDirection, int yDirection) {
        if (getElement((x + xDirection), (y + yDirection)) instanceof NoneElement) {
            setElement((x + xDirection), (y + yDirection), element);
            setElement(x, y, new NoneElement());
        }
    }

    public void checkAndMoveBulletOneStep(int x, int y, GameElement element, int xDirection, int yDirection) {
        if (getElement((x + xDirection), (y + yDirection)) instanceof NoneElement) {
            setElement((x + xDirection), (y + yDirection), element);
            setElement(x, y, new NoneElement());

        } else if (element instanceof BulletElement) {
            checkAndHitTank(x, y, xDirection, yDirection);
            setElement(x, y, new NoneElement());
        }
    }

    private void checkAndHitTank(int x, int y, int xDirection, int yDirection) {
        if (getElement((x + xDirection), (y + yDirection)) instanceof BootElement) {
            hitBootTank(x, y, xDirection, yDirection);
        } else if (getElement((x + xDirection), (y + yDirection)) instanceof UserTankElement) {
            hitUserTank(x, y, xDirection, yDirection);
        }
    }

    public void displayGame() {
        gridPane.getChildren().clear();
        for (int x = 0; x < 29; x++) {
            for (int y = 0; y < 29; y++) {
                ImageView imageView = getElement(x, y).getImage();
                gridPane.add(imageView, x, y);
            }
        }
    }

    public void handleClick(KeyCode code) {
        if (code == KeyCode.SPACE) {
            fireBullet();
        } else {
            findElementAndChangeItsDirection(code);
        }
    }

    public void doMove() {
        findBootAndChangeItsDirection();
        moveTanks();
        moveBullet();
        displayGame();
        label.setText("Boot [" + getPointsBoot() + " : " + getPointsUser() + "] User");
        switchTurn();
    }

    public void doFireBoot() {
        fireBulletBoot();
    }
}
