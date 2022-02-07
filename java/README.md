**JDK** : OpenJDK 17 (jigsaw, sealed classes, text block) \
**Test** : JUnit 5 \
**IDE** : Intellij IDEA

#### Roadmap
1. [x] **Tag** refactoring-v1 : code concis et éliminer la duplication
2. [x] **Tag** refactoring-v2 : généraliser et découpler le scoring
3. [ ] Généraliser le lancer de dés / le score (nombre de dés change, typage différent, nouvelles propriétés)

```java
// refactoring-v2
YatzyGame game = new YatzyGame(List.of(
            new Chance(),
            new Yatzy(),
            new One(),
            new Two(),
            new Three(),
            new Four(),
            new Five(),
            new Six(),
            new OnePair(),
            new TwoPairs(),
            new ThreeOfAKind(),
            new FourOfAKind(),
            new FullHouse(),
            new SmallStraight(),
            new LargeStraight()
        ));

game.score(YATZY, 1, 1, 1, 1, 1); // -> 50
```

```java
// refactoring-v1
YatzyGame game = new YatzyGame(1, 1, 1, 1, 1);
game.yatzy(); // -> 50
```