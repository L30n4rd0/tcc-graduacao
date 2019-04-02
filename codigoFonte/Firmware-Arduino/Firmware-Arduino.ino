#include <Servo.h>

Servo servoMotorX, servoMotorY;
String value; //variável que receberá os valores da porta serial
char character;

void setup() {
  Serial.begin(9600);

  servoMotorX.attach(8);
  servoMotorY.attach(9);

  servoMotorX.write(90);
  servoMotorY.write(90);

  String value = "";

}

void loop() {
  //verifica se existe algum dado na porta serial
  if (Serial.available()) {

    character = Serial.read();

    if (character == 'a') {
      servoMotorX.write( value.toInt() );
      value = "";

    } else if (character == 'b') {
      servoMotorY.write( value.toInt() );
      value = "";

    } else if (character == 'c') {
//      executa do código de controle de locomoção
        
        switch( value.toInt() ){
          case 7:
//          inserir código referente à locomoção para frente

          break;

          case 8:
//          inserir código referente à locomoção para direita

          break;

          case 9:
//          inserir código referente à locomoção para trás

          break;

          case 10:
//          inserir código referente à locomoção para esquerda

          break;

          case 11:
//          inserir código de parar locomoção

          break;
        }

      value = "";

    } else {
      value.concat(character);

    }
  }
}
