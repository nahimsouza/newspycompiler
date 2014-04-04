#include <stdio.h>
#include <stdlib.h>
 
// Define o tipo Func que eh um ponteiro para funcao 
typedef void (*Func)();
 
int main() {
  
typedef struct _St_A {  
	Func *vt; 
	} _class_A;  

	class_A *new_A(void); 
     {

    void _CN_m(    _class_CN *this    )         {

      printf(      "       %s      "      ,       " 7 "      );
      if(      1.0      >      0.0      )      {

        printf(        "         %s        "        ,         " 0 "        );
      }
      if(      1.0      >=      0.0      )      {

        printf(        "         %s        "        ,         " 1 "        );
      }
      if(      1.0      !=      0.0      )      {

        printf(        "         %s        "        ,         " 2 "        );
      }
      if(      0.0      <      1.0      )      {

        printf(        "         %s        "        ,         " 3 "        );
      }
      if(      0.0      <=      1.0      )      {

        printf(        "         %s        "        ,         " 4 "        );
      }
      if(      0.0      ==      0.0      )      {

        printf(        "         %s        "        ,         " 5 "        );
      }
      if(      0.0      >=      0.0      )      {

        printf(        "         %s        "        ,         " 6 "        );
      }
      if(      0.0      <=      0.0      )      {

        printf(        "         %s        "        ,         " 7 "        );
      }
      if(      1.0      ==      0.0      )      {

        printf(        "         %s        "        ,         " 18 "        );
      }
      if(      0.0      >      1.0      )      {

        printf(        "         %s        "        ,         " 10 "        );
      }
      if(      0.0      >=      1.0      )      {

        printf(        "         %s        "        ,         " 11 "        );
      }
      if(      0.0      !=      0.0      )      {

        printf(        "         %s        "        ,         " 12 "        );
      }
      if(      1.0      <      0.0      )      {

        printf(        "         %s        "        ,         " 13 "        );
      }
      if(      1.0      <=      0.0      )      {

        printf(        "         %s        "        ,         " 14 "        );
      }
    }
  }
  

Func VTclass_A[] = {
	  ( void (*)() ) _A_m  
  };

  

_class_A *new_A()
{
 _class_A *t;
    if ( (t = malloc(sizeof(_class_A))) != NULL )
	  t->vt = VTclass_A;  
  return t;
 } 

  _a  =  _A  (  )  ;   
  printf(  "   %s  "  ,   ""  );
  printf(  "   %s  "  ,   "ok-Ger01"  );
  printf(  "   %s  "  ,   "The output should be : "  );
  printf(  "   %s  "  ,   "7 0 1 2 3 4 5 6 7"  );
  _a  ->vt[  _m  ]  (  _a  )  ;   
   
  return 0;
}
