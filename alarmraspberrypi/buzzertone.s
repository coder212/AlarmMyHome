@test.s using gpio for testing  wiringpi and buzzer
.data
.balign 4
Intro: .asciz "buzzer tone project\n"
ErrMsg: .asciz "setup tidak berjalan... aborting...\n"
pin: .int 7
i: .int 0
delayMs: .int 250
OUTPUT = 1
.text 
.global main
.extern printf
.extern wiringPiSetup
.extern delay
.extern digitalWrite
.extern pinMode

main:
   PUSH {IP, LR}
   LDR R0, =Intro
   BL printf
   BL wiringPiSetup
   MOV R1, #-1
   CMP R0, R1
   BNE init
   LDR R0, =ErrMsg
   BL printf
   B done

   init:
      LDR R0, =pin
      LDR R0, [R0]
      MOV R1, #OUTPUT
      BL pinMode

  LDR R4, =i
  LDR R4, [R4]
  MOV R5, #10

forLoop:
  CMP R4, R5
  BGT done

  LDR R0, =pin
  LDR R0, [R0]
  MOV R1, #1
  BL digitalWrite

  LDR R0, =delayMs
  LDR R0, [R0]
  BL delay

  LDR R0, =pin
  LDR R0, [R0]
  MOV R1, #0
  BL digitalWrite

  LDR R0, =delayMs
  LDR R0, [R0]
  BL delay

  ADD R4, #1
  B forLoop
done:
  LDR R0, =pin
  LDR R0, [R0]
  MOV R1, #0
  BL pinMode
  POP {IP, PC}
