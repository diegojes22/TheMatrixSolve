import matplotlib.pyplot as plt
import math
import sys

# polinomio
def polinomio(x, p:str):
    '''
    Convertir un string a un polinomio
    '''

    p.replace('x', str(x))
    return eval(p)

def replace_constant(constant : str, polinomio : str):
    try :
        if(constant == 'e'):
            polinomio.replace(constant, str(math.e))
        elif(constant == 'pi'):
            polinomio.replace(constant, str(math.pi))

        
    except Exception as e:
        print(e)

    return polinomio

    

def polinomio_por_argumento(x):
    p : str = sys.argv[1]

    p.replace('x', str(x))
    return eval(p)


print(sys.argv)
step: float = -10
while(step < 10):
    plt.plot(step, polinomio_por_argumento(step), '-.bo', label="f(x)="+sys.argv[1])
    step += 0.1

plt.axhline(0, color="black")
plt.axvline(0, color="black")

plt.show()
