
 program ScopeTest;
 var
   x, tmp: integer; { Declare globally }

 procedure TestProc(y: integer);
 var
   z: integer; { Declare at procedure level }
 begin
     x := y;
     z := 5;

     while z > 0 do
     begin
          writeln('Hi');
         tmp := 10;
         z := z - 1;
     end;
 end;

 begin
     x := 10;
     TestProc(x);
 end.
