Problem Set 1
Name: Niu Yunpeng
Matric Number: A0162492A

Problem 1
(c) See PSOne.java, notice: line 26 is a comment indicating the result.
(d) The result of MysteryFunction is argA in power of argB. In other words, it does somehow the
same work as Math.pow(double argA, double argB). For example, MysteryFunction(5, 4) will return 625, 
since 5 * 5 * 5 * 5 = 625.
However, another difference with Math.pow is that, this MysteryFunction only receives integers as
paramets, so it also returns an integer. It cannot calculate fraction numbers (so-called double).

Problem 2
(a) See ShiftRegister.java, this class implements ILFShiftRegister.java
(b) The two sample tests can be seen in PS2Test1.java and PS2Test2.java, and the other test cases 
are all in ShiftRegisterTest.java
(c) This question is about an important kind of error handling, namely illegal input. In the class
ShiftRegister, input verification is used for both constructor and setSeed. Also, the method shift 
and generate will throw errors when the verification has already failed for setSeed or there hasn't
been call to setSeed yet. See the comments in ShiftRegister.java for more details.
There are a few test cases in ShiftRegisterTest.java, testing the following conditions respectively:
when the input size is larger than what we need, when the input size is smaller than what we need, 
when some of the input values in the elements of seeds are bad, when the input value of size is bad,
when the input value of tap is bad.
(d) See mystery_decoded.png for a screenshot. It is the picture of all CS2020 tutors. Notice that a 
few lines are added into ImageEncode.java
(e) For repetition, see ShiftRegisterRepeat.java for the test program. Not all taps are equally 
good. Actually, if you change the value of tap, you may find that the difference is quite obvious.
For string encryption, see StringEncode.java for demonstration. This class converts a string into
an array of 0's and 1's. Each character in the string becomes an 8-bit binary.
See Pseudorandom.java for details. The result shows the number of 1's and 0's are roughly the same.
However, it may not be good enough to be used as a pseudorandom number generator.

Problem 3
See PS3.java for the code. The version EvenBetterVectorTextFile.java is used and it outputs a table
representing the angles (in radius) for any two of the text files.
The threshold value should be about 0.41.
Hamlet, Henryv, Macbeth and mystery are written by Shakespeare. Cromwell and Vortigern are not written
by Shakespeare.