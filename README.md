# Ladder Logic Parser to Propositional Logic

Parses a small textual ladder diagram DSL into propositional logic expressions.

## DSL Overview

Keywords / tokens:
- `LPR` : start of a rung (left power rail)
- `CONTACT X` : normally open contact with variable `X`
- `NCONTACT X` or `!CONTACT X` : negated contact (normally closed)
- `--` : horizontal link (series / logical AND)
- `(` `|` `)` : vertical branch group. Alternatives separated by `|` (logical OR). Inside each branch alternative contacts can be chained with `--` for AND.
- `COIL Y` : terminating coil with output variable `Y`

Rung structure: `LPR <series until COIL> COIL <IDENT>`

Examples:
```
LPR CONTACT A -- CONTACT B COIL Q        # Q := A ∧ B
LPR CONTACT A -- ( CONTACT B | !CONTACT C ) COIL Q   # Q := A ∧ (B ∨ ¬C)
LPR ( CONTACT A -- CONTACT B | !CONTACT C ) COIL R   # R := (A ∧ B) ∨ ¬C
```

## Translation Rules
- Series of factors => conjunction (∧)
- Branch group => disjunction (∨) of alternatives; each alternative may itself be a conjunction
- Negated contact => ¬X (or `!X` in ASCII)

## Build & Test (Windows PowerShell)
```
./gradlew.bat test
```
(If wrapper jar missing, run `gradle wrapper` first.)

## CLI Usage
```
./gradlew.bat run --args="path/to/file.ld --format=ascii"
# or:
ladder "LPR CONTACT A -- CONTACT B COIL Q" | java -jar build/libs/ladder-logic-parser-0.1.0.jar
```
Output format defaults to Unicode symbols; use `--format=ascii` for `&`, `|`, `!`.

## Future Improvements
- More ladder elements (timers, set/reset coils)
- Validation of contact repetition
- Better error messages with position info
