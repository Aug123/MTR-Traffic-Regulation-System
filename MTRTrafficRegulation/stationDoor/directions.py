'''
Created on 4 avr. 2018

@author: laptop
'''
def direction_right(sense, color=(255, 255, 255)):
    """used to print an arrow in the right direction"""
    if not sense:
        raise Exception("Sense HAT is unavailable")
    sense.clear()    
    for column in range(0, 4):
        sense.set_pixel(column,4,color)
        sense.set_pixel(column,3,color)
    
    for row in range(0,8):
        sense.set_pixel(4,row,color)
    for row in range(1,7):
        sense.set_pixel(5,row,color)
    for row in range(2,6):
        sense.set_pixel(6,row,color)
    for row in range(3,5):
        sense.set_pixel(7,row,color)    
        
def direction_left(sense, color=(255,255,255)):
    if not sense:
        raise Exception("Sense HAT is unavailable")
    sense.clear()
        
    for column in range(4, 8):
        sense.set_pixel(column,4,color)
        sense.set_pixel(column,3,color)
    
    for row in range(0,8):
        sense.set_pixel(3,row,color)
    for row in range(1,7):
        sense.set_pixel(2,row,color)
    for row in range(2,6):
        sense.set_pixel(1,row,color)
    for row in range(3,5):
        sense.set_pixel(0,row,color)
        
def direction_down(sense, color=(255,255,255)):
    
    if not sense:
        raise Exception("Sense HAT is unavailable")
    sense.clear()
    
    for raw in range(0, 4):
        sense.set_pixel(4,raw,color)
        sense.set_pixel(3,raw,color)
    
    for column in range(0,8):
        sense.set_pixel(column,4,color)
    for column in range(1,7):
        sense.set_pixel(column,5,color)
    for column in range(2,6):
        sense.set_pixel(column,6,color)
    for column in range(3,5):
        sense.set_pixel(column,7,color)
        


def diplay_good_direction(sense, d, col):
    
    if not sense:
        raise Exception("Sense HAT is unavailable")
    
    if col=='255,0,0\n':
        color=(255, 0, 0)
    elif col=='0,255,0\n':
        color=(0, 255, 0)
    else :
        color=(255, 128, 0)


    if d==-1:
        direction_left(sense, color)
    elif d==0:
        direction_down(sense, color)
    elif d==1:
        direction_right(sense, color)
    else:
        raise Exception("Identification of the direction not -1, 0 or 1")
    
    
    
    
