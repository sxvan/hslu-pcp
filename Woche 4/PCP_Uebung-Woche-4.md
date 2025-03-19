# Woche 4

## Aufgabe 1

-   **a**: Die query läuft unendlich bis der Fehler: "Stack limit exceeded" auftritt und das Programm beendet wird.
-   **b**: Das Problem tritt auf, weil Prolog Werte für N einsetzt bis dieser zum gewünschten F Wert passt. Da ein solcher Wert nicht existiert, werden undendlich Werte eingesetzt. Aufgrund der Head-First Rekursion des Prädikats überläuft der Stack.
-   **c**: Das Problem lässt sich auf mehrere Arten beheben. Zum einen könnte die Rekursion in Tail-First geändert werden, was den Stack entlastet. Trotzdem würde das Programm unendlich laufen. Um das Problem ganz zu beheben muss eine Einschränkung für die N Werte definiert werden. In meiner Lösung habe ich die Einschränkung `N =< 2*F` definiert. Diese ist sicherlich nicht perfekt aber löst das Problem.

## Aufgabe 2

```
set_difference([], _, []).

set_difference([H|T], Set2, [H|SetDifference]) :-
  \+ member(H, Set2),
  set_difference(T, Set2, SetDifference).

set_difference([H|T], Set2, SetDifference) :-
  member(H, Set2),
  set_difference(T, Set2, SetDifference).
```

## Aufgabe 3

-   **a**: `?- { T = 15, M = 3*T, (M+J)/2 = T+J  }.` J = 15
-   **b**:
    1 ?- donald_gerald_robert(As + Bs = Cs).
    As = [5, 2, 6, 4, 8, 5],
    Bs = [1, 9, 7, 4, 8, 5],
    Cs = [7, 2, 3, 9, 7, 0]

## Aufgabe 4

```
:- use_module(library(http/http_client)).
:- use_module(library(http/http_json)).
:- use_module(library(clpfd)).

% relationship -------------------------------------------------------------------------
female(mary). female(liz). female(mia). female(tina). female(ann). female(sue). % all females
male(mike). male(jack). male(fred). male(tom). male(joe). male(jim).            % all males
parent(mary, mia). parent(mary, fred). parent(mary, tina).                      % all childern of mary
parent(mike, mia). parent(mike, fred). parent(mike, tina).                      % all children of mike
parent(liz, tom). parent(liz, joe).                                             % all childern of liz
parent(jack, tom). parent(jack, joe).                                           % all childern of jack
parent(mia, ann).                                                               % all childern of mia
parent(tina, sue). parent(tina, jim).                                           % all childern of tina
parent(tom, sue). parent(tom, jim).                                             % all childern of tom


mother(X, Y) :- female(X), parent(X, Y).
father(X, Y) :- male(X), parent(X, Y).

sibling(X, Y) :- X \= Y, parent(Z, X), parent(Z, Y).

grandparent(X, Y) :- parent(X, Z), parent(Z, Y).
grandfather(X, Y) :- male(X), grandparent(X, Y).
grandmother(X, Y) :- female(X), grandparent(X, Y).

offspring(X, Y) :- parent(Y, X).
offspring(X, Y) :- grandparent(Y, X).
% --------------------------------------------------------------------------------------



% sudoku -------------------------------------------------------------------------------
replace_0([], []).
replace_0([0|T], [_|R]) :- replace_0(T, R).
replace_0([H|T], [H|R]) :- H \= 0, replace_0(T, R).

solve_sudoku(Rows) :-
  append(Rows, Vs), Vs ins 1..9,
  maplist(all_distinct, Rows),
  transpose(Rows, Columns),
  maplist(all_distinct, Columns),
  Rows = [A, B, C, D, E, F, G, H, I],
  blocks(A, B, C), blocks(D, E, F), blocks(G, H, I),
  maplist(label, Rows).

blocks([], [], []).
blocks([A, B, C|Bs1], [D, E, F|Bs2], [G, H, I|Bs3]) :-
  all_distinct([A, B, C, D, E, F, G, H, I]),
  blocks(Bs1, Bs2, Bs3).
% --------------------------------------------------------------------------------------


solve(Problem, Id) :-
  format(string(SolutionUrl), 'http://localhost:16316/problem/~w/', [Problem]),
  string_concat(SolutionUrl, Id, ProblemUrl),

  http_get(ProblemUrl, Reply, [json_object(dict)]),
  call(Problem, Reply, Solution),
  http_post(SolutionUrl, json(json([solution=Solution, id=Id])), _, []).

relationship(Data, Solution) :-
  atom_string(FirstPerson, Data.firstPerson),
  atom_string(SecondPerson, Data.secondPerson),
  atom_string(Relationship, Data.relationship),
  call(Relationship, FirstPerson, SecondPerson) -> Solution = "true" ; Solution = "false".

sudoku(Data, Solution) :-
  maplist(replace_0, Data.sudoku, Solution),
  solve_sudoku(Solution),
  !. % prevent backtracking
```
