#include <cocheDuino.h>

#include <Bridge.h>
#include <YunServer.h>
#include <YunClient.h>

#define PARARCOCHE 0
#define ADELANTECOCHE 1
#define ATRASCOCHE 2
#define DERECHACOCHE 3
#define IZQUIERDACOCHE 4
#define LUCES 5

#define PINLUZ 13
#define PINM1 3
#define PINM2 4
#define PINM3 5
#define PINM4 6

YunServer server;
bool lucesOn=0;
cocheDuino coche(PINM1,PINM2,PINM3,PINM4);

void setup() {
  
  Bridge.begin();
  server.listenOnLocalhost();
  server.begin();
  pinMode(PINLUZ,OUTPUT);

}

void loop() {
  YunClient client= server.accept();
  if(client){
     process(client);
    client.stop();
  }
  delay(50);
}

void process(YunClient client){
  String command = client.readStringUntil('/');
  printHeader(client);
  if(command=="control"){
     processControl(client); 
  }
 
 

  
}

void printHeader(YunClient client){
  client.println("Status: 200");
 client.println("Content-type: text/html");
 client.println();
}

void processControl(YunClient client){
   int comando = client.parseInt();
   client.print("Comando: ");
   client.println(comando);
   switch(comando){
     
    case PARARCOCHE:
      client.println("Parando");
      coche.parar();
      break;
    case ADELANTECOCHE:
      client.println("ADELANTE");
      coche.avanzar(255, ADELANTE);
      break;
     case ATRASCOCHE:
      client.println("ATRAS");
      coche.avanzar(255,ATRAS);
      break;
     case DERECHACOCHE:
     client.println("DERECHA");
     coche.girar(255,DERECHA);
     break;
     case IZQUIERDACOCHE:
     client.println("IZQUIERDA");
     coche.girar(255,IZQUIERDA);
     break;
     case LUCES:
       lucesOn=!lucesOn;
       digitalWrite(PINLUZ,lucesOn);
       break;
      default:
        client.println("otro");
   }  
}
