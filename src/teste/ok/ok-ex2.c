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
    void _CN_get(    _class_CN *this    )         {

      return       this->_CN      _i      ;    }
    void _CN_put(    _class_CN *this    ,     _p_i    )         {

      this->_CN_      _i      =      _p_i      ;       
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

	class_B *new_A(void); 
     {

    _num    =    0.0    ;     
    _a    =    _A    (    )    ;     
    void _CN_put(    _class_CN *this    ,     _n    )         {

      this->_CN_      _num      =      _n      ;       
      _a      ->vt[      _put      ]      (      _a      ,       _n      )      ;       
    }
    void _CN_write(    _class_CN *this    )         {

      printf(      "      self      func      "      ,       this->_CN      _num      ,       _a      ->vt[      _get      ]      (      _a      ,       )      );
    }
    void _CN_inc(    _class_CN *this    )         {

      this->_CN_      _num      +=      this->_CN      _num      ;       
    }
    void _CN_getNum(    _class_CN *this    )         {

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
  printf(  "  func  "  ,   _b  ->vt[  _getNum  ]  (  _b  ,   )  );
   
  return 0;
}
