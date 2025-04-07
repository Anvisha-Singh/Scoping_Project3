program LoopDemo;
var
  counter: integer;
begin
  counter := 0;

  while counter < 3 do
  begin
    writeln('Hi');
    counter := counter + 1;
    if counter = 3 then break;
  end;
end.
