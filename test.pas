program SmallTest;
var
  x: integer;

begin
  x := 10;

  while x > 0 do
  begin
    writeln(x);
    x := x - 1;

    if x = 5 then
      break;  { Valid use of break inside the loop }
  end;
end.
