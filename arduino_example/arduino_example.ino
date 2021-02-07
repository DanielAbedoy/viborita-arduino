#include "Pines.h"

void setup() {
  Serial.begin(9600);

  pinMode(BOTON_ARRIBA,INPUT_PULLUP);
  pinMode(BOTON_ABAJO,INPUT_PULLUP);
  pinMode(BOTON_DERECHA,INPUT_PULLUP);
  pinMode(BOTON_IZQUIERDA,INPUT_PULLUP);
}

void loop() {
    if(digitalRead(BOTON_ARRIBA) == LOW){
      Serial.println("u");
      
    }else if(digitalRead(BOTON_ABAJO) == LOW){
      Serial.println("d");
      
    }else if(digitalRead(BOTON_DERECHA) == LOW){
      Serial.println("r");
      
    }else if(digitalRead(BOTON_IZQUIERDA) == LOW){
      Serial.println("l");
    }


    if(analogRead(PALANCA_X) < 500){
      Serial.println("l");
    } else if(analogRead(PALANCA_X) > 600){
      Serial.println("r");
    } else if(analogRead(PALANCA_Y) < 500){
      Serial.println("d");
    } else if(analogRead(PALANCA_Y) > 600){
      Serial.println("u");
    }
    
}
