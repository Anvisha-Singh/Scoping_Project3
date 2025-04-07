program ScopeTest;
var
  x, tmp: integer;

procedure TestProc(y: integer);
var
  z: integer;
begin
    x := y;
    z := 5;

    while z > 0 do
    begin

        tmp := 10;
        z := z - 1;
    end;
end;

begin
    x := 10;
    z := 10;
    TestProc(x);
end.
