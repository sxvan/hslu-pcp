set_difference([], _, []).

set_difference([H|T], Set2, [H|SetDifference]) :- 
  \+ member(H, Set2),
  set_difference(T, Set2, SetDifference).

set_difference([H|T], Set2, SetDifference) :- 
  member(H, Set2),
  set_difference(T, Set2, SetDifference).