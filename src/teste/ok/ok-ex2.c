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

    _i    =    0.0    ;     
    void _A_get(    _class_A *this    )         {

      return       this->_CN      _i      ;    }
    void _A_put(    _class  _A *this    ,     _p_i    )         {

      this->_CN      _i      =      _p_i      ;       
    }
  }
  

Func VTclass_A[] = {
	  ( void (*)() ) _A_get  ,  ( void (*)() ) _A_put  
  };

  

_class_A *new_A()
{
 _class_A *t;
    if ( (t = malloc(sizeof(_class_A))) != NULL )
	  t->vt = VTclass_A;  
  return t;
 } 

  
typedef struct _St_B {  
	Func *vt; 
	} _class_B;  

	class_B *new_B(void); 
     {

    _num    =    0.0    ;     
    _a    =    _A    (    )    ;     
    void _B_put(    _class_B *this    ,     _n    )         {

      this->_CN      _num      =      _n      ;       
      _a      ->vt[      _put      ]      (      _a      ,       _n      )      ;       
    }
    void _B_write(    _class_B *this    )         {

      printf(      "      %d      %d      "      ,       this->_CN      _num      ,       _a      ->vt[      _get      ]      (      _a      ,       )      );
    }
    void _B_inc(    _class_B *this    )         {

      this->_CN      _num      +=      this->_CN      _num      ;       
    }
    void _B_getNum(    _class_B *this    )         {

      return       this->_CN      _num      ;    }
  }
  

Func VTclass_B[] = {
	  ( void (*)() ) _B_put  ,  ( void (*)() ) _B_write  ,  ( void (*)() ) _B_inc  ,  ( void (*)() ) _B_getNum  
  };

  

_class_B *new_B()
{
 _class_B *t;
    if ( (t = malloc(sizeof(_class_B))) != NULL )
	  t->vt = VTclass_B;  
  return t;
 } 

  _k  =  0.0  ;   
  _a  =  _A  (  )  ;   
  _a  ->vt[  _put  ]  (  _a  ,   5.0  )  ;   
  _k  =  _a  ->vt[  _get  ]  (  _a  ,   )  ;   
  printf(  "   %s  "  ,   _k  );
  _b  =  _B  (  )  ;   
  _b  ->vt[  _put  ]  (  _b  ,   2.0  )  ;   
  _b  ->vt[  _inc  ]  (  _b  )  ;   
  _b  ->vt[  _write  ]  (  _b  )  ;   
  printf(  "  %d  "  ,   _b  ->vt[  _getNum  ]  (  _b  ,   )  );
   
  return 0;
}
