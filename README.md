# Image-Evolution
- Generative Image from input image with shapes. 

Given any Image, PUBG mobile logo, for example,

![pubgMobile](https://github.com/jonesyu30/Image-Evolution/assets/66526414/edf4213e-e248-4d08-af53-280bbab8cdca)

It turns into a video similar to this, with random algorithm.

https://github.com/jonesyu30/Image-Evolution/assets/66526414/7914a160-2362-4355-955a-f620cfaf331f


# Variables
- NUMBER_OF_IMAGES:
  - Number of images generated per iteration.
  - Poportional to success rate of each iteration but sacrifices performance.
 
- NUMBER_OF_SHAPES:
  - Total number of shapes on the final video.
  - Poportional to details shown in the final restult.

- MAX_IMAGES_SHOWN:
  - Total number of images shown while generating.
  
- imageMaxHeight, imageMaxWidth:
  - Number of pixels in final result.
  - Poportional to details shown in the final restult.

- newShape.changeLittle(30) :
  - Default value is 30.
  - Poportional to the difference of clones from last generation.
  - Effect: unknown......
 

