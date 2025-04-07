program SmallTest;
var
  x: integer;

begin
  x := 10;

  while x > 0 do
  begin
    x := x - 1;

    if x = 5 then
      break;
  end;
end.
