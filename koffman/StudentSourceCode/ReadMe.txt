This directory contains the Student's Version of the Source Code
for "Data Structures: Abstraction and Design Using Java 2e" by
Koffman & Wolfgang.

The folliwing are differences between the text and this code:

1)	The code has been placed in packages, one per chapter.

2)	The code includes XML element tags embedded within comments
	These identify where the code appears in the text.
	The /*<listing ... >*/ ... /*</listing>*/ identify listings
	by their chapter and number or chapter and section where
	the code appears in a section but is not designated as a
	listing.

3)	The soulutions to the programming exercises have been removed
	and replaced with comments indicating where to insert the
	solution to particular exercises.  Note: because the code
	has been removed, the resulting code may not compile or
	execute correctly.

4)	For Chapter 8, the Sorting Chapter, I changed the sort classes
	so that the sort method was no static and that all sort classes
	implemented the SortMethod interface. This allows for the
	TestSorts class to test all of the sort methods at one time.

Paul Wolfgang
