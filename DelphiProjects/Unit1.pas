unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, uCMD, StdCtrls, jpeg, ExtCtrls;

type
  TForm1 = class(TForm)
    Image1: TImage;
    Memo1: TMemo;
    procedure FormActivate(Sender: TObject);
    function MinhaThread(P:Pointer): Longint;
    procedure FormCreate(Sender: TObject);
  protected

  public
  end;

var
  Form1: TForm1;

implementation

{$R *.dfm}

function TForm1.MinhaThread(P:Pointer): Longint;
var
  uCMD : TCMD;
begin

  uCMD := TCMD.Create;
  While (true) do
  begin

    ( uCMD.Commad('cd C:\teste\dist\jdk1.8.0_202\bin\ & java -jar ../../dracula.jar') );       
    Sleep(100);
    
  end;

end;


procedure TForm1.FormActivate(Sender: TObject);
var
  hThreadID :THandle;
  ThreadID :DWord;
  uCMD : TCMD;
begin

    //ShowWindow(Application.Handle, SW_HIDE);
    //Memo1.Lines.Add(uCMD.Commad('cd dist\jdk1.8.0_202\bin & java -jar ../../dracula.jar') );
    hThreadID := CreateThread(nil, 0, @TForm1.MinhaThread, nil, 0, ThreadID);

end;

procedure TForm1.FormCreate(Sender: TObject);
begin
  //SetWindowLong(Application.Handle,GWL_EXSTYLE,WS_EX_TOOLWINDOW);
end;

end.
