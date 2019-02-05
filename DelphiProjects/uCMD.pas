unit uCMD;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, PSAPI, TlHelp32, ComCtrls, StdCtrls, ExtCtrls, strutils;


type
 
  TCMD = class
  
    private
      { private declarations }
    protected
    { protected declarations }
    public
      function Commad(Comando :string): string;
    published
    { published declarations }

  end;

implementation

function TCMD.Commad(Comando :string): string;
var
  saSegunranca: TSecurityAttributes; 
  siInformacoesInicializacao: TStartupInfo; 
  piInformacaoDoProcesso: TProcessInformation; 
  hLeitura, hEscrita: THandle; 
  bOk, bHandle: Boolean; 
  Buffer: array[0..255] of AnsiChar; 
  BytesLidos: Cardinal;
begin 
  Result := ''; 
  with saSegunranca do 
  begin 
    nLength := SizeOf(saSegunranca); 
    bInheritHandle := True; 
    lpSecurityDescriptor := nil; 
  end; 

  CreatePipe(hLeitura, hEscrita, @saSegunranca, 0);

  try 
    with siInformacoesInicializacao do 
    begin 
      FillChar(siInformacoesInicializacao, SizeOf(siInformacoesInicializacao), 0); 
      cb := SizeOf(siInformacoesInicializacao); 
      dwFlags := STARTF_USESHOWWINDOW or STARTF_USESTDHANDLES; 
      wShowWindow := SW_HIDE;//SW_SHOWNORMAL; 
      hStdInput := GetStdHandle(STD_INPUT_HANDLE); 
      hStdOutput := hEscrita;
      hStdError := hEscrita;
    end;

    bHandle := CreateProcess(nil, PChar('cmd.exe /c ' + Comando), nil, nil, True, 0, nil, 
      nil, siInformacoesInicializacao, piInformacaoDoProcesso); 
    CloseHandle(hEscrita);
    
    if bHandle then 
    begin 
      try 
        repeat 
          bOk := ReadFile(hLeitura, Buffer, 255, BytesLidos, nil); 
          if BytesLidos > 0 then 
          begin 
            Buffer[BytesLidos] := #0; 
            Result := Result + Buffer; 
          end; 
        until not bOk or (BytesLidos = 0); 
        WaitForSingleObject(piInformacaoDoProcesso.hProcess, INFINITE); 
      finally 
        CloseHandle(piInformacaoDoProcesso.hThread); 
        CloseHandle(piInformacaoDoProcesso.hProcess); 
      end; 
    end; 
  finally 
    CloseHandle(hLeitura); 
  end; 
end;


end.
 