import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TestApp6 {

	public static void main(String[] args) {


		//NOTE TO NEW USER: The created file is written at "/Users/EXAMPLEUSER/Desktop/MIDIFiles/Samples/exampleMIDI.txt" which is close to 		// the end of the program.
		// IN ORDER TO SEE RESULTS, CHANGE THE FILE PATH TO ONE ONE YOUR SYSTEM.
		//THEN USING HEXEDIT OR SOMETHING SIMILAR, CONVERT THE FILE FROM txt to MIDI
		


		// CURRENT PROBLEMS TO FIX: Whole note and length issue
		//
		

		// Info regarding arrays: PitchArray: what the pitch is: 3C, 4G, etc.
		// Within a key, so labeled P4, M6 etc.
		// RhythmArray: Gives you, in order, the list of note lengths, labeled
		// in 8 (eighth note), 4 (quarter note), or 2 (half note).
		// VelocityArray: Gives you, in order, the velocities. Limited to p or f
		// in this case. Accented or unaccented.
		// BitCountArray: Gives you a detailed count of where the music is,
		// within a 4 bar phrase. 60 bits per beat.
		// MeasureCountArray: counts the number of measures
		// InterMeasureCount: Counts where within a measure we are.
		// PhraseCountArray: Counts which phrase we are on. The overall value of
		// this
		// depends on the length of the piece.

		ArrayList<String> PitchArray = new ArrayList<String>();
		ArrayList<Integer> PitchNumberArray = new ArrayList<Integer>();
		ArrayList<Double> RhythmArray = new ArrayList<Double>();
		ArrayList<Integer> VelocityArray = new ArrayList<Integer>();
		ArrayList<Integer> BitCountArray = new ArrayList<Integer>();
		ArrayList<Integer> InterMeasureCountArray = new ArrayList<Integer>();
		ArrayList<Integer> ExtraMeasureCountArray = new ArrayList<Integer>();
		ArrayList<Integer> PhraseCountArray = new ArrayList<Integer>();

		ArrayList<String> HarmonicTemplate = new ArrayList<String>();

		// Setting all the 0 (first) values to 0.

		BitCountArray.add(0);
		InterMeasureCountArray.add(1);
		ExtraMeasureCountArray.add(1);
		PhraseCountArray.add(1);

		/*
		 * there need to be at least two counts going on: One that tells you how
		 * many beats you're in and one that tells you where in the beat you are.
		 */
		// phrasecount counts the number of phrases. In this example there would
		// be 2 phrases (4 bars each).
		int phrasecount = 1;
		// bitcount counts the place in the beat.
		// 240 parts per bar. 60 parts per quarter note. every measure it loops.
		int bitcount = 0;
		int intermeasurecount = 1;
		int extrameasurecount = 1;

		int i = 0;
		int numberof8thnotes = 0;

		// ALL OF THIS IS FOR THE NEW NOTE
		// This is for random numbers. rand1 decides octave. rand2 decides place
		// in scale. rand3 decides rhythm.
		// rand4 velocity decides whether it'll be forte or piano. rand5
		// velocity chooses a specific piece within that range.
		Random prand = new Random();
		int startingKey = 55;
		int lastOctave = 3;

		
			while (phrasecount < 3) {

				// You have to divide the randomization into different parts.
				// The count stays the same.

				if (i == 0) {
					

					Random rand = new Random();
					// for now, rand 1 decides pitch within a certain range.
					int rand1 = rand.nextInt(600);
					// rand2 goes between 6 notes, major 7th is not a viable
					// last
					// note.
					// rand3 is either eighth note, quarter note, or whole note.
					
					int rand4velocity = rand.nextInt(2);
					int rand5velocity = rand.nextInt(10);
					// C stands for current
					int Cvelocity = 54 + (rand4velocity * 36) + rand5velocity;

					VelocityArray.add(Cvelocity);

					String CpitchS = null;
					int CpitchN = 0;

					// It seems that the range is basically a 4th below the root
					// up to a third above the next highest root.
					if (rand1 >= 550) {
						CpitchS = lastOctave + "R";
						CpitchN = startingKey;

					} else if (rand1 >= 535) {
						CpitchS = lastOctave + "M2";
						CpitchN = 2 + startingKey;

					} else if (rand1 >= 460) {
						CpitchS = lastOctave + "M3";
						CpitchN = 4 + startingKey;

					} else if (rand1 >= 445) {
						CpitchS = lastOctave + "P4";
						CpitchN = 5 + startingKey;

					} else if (rand1 >= 380) {
						CpitchS = lastOctave + "P5";
						CpitchN = 7 + startingKey;

					} else if (rand1 >= 370) {
						CpitchS = lastOctave + "M6";
						CpitchN = 9 + startingKey;

					} else if (rand1 >= 310) {
						CpitchS = (lastOctave + 1) + "R";
						CpitchN = 12 + startingKey;

					} else if (rand1 >= 300) {
						CpitchS = (lastOctave + 1) + "M2";
						CpitchN = 14 + startingKey;

					} else if (rand1 >= 220) {
						CpitchS = (lastOctave + 1) + "M3";
						CpitchN = 16 + startingKey;

					} else if (rand1 >= 200) {
						CpitchS = (lastOctave + 1) + "P4";
						CpitchN = 17 + startingKey;

					} else if (rand1 >= 120) {
						CpitchS = (lastOctave + 1) + "P5";
						CpitchN = 19 + startingKey;

					} else if (rand1 >= 100) {
						CpitchS = (lastOctave + 1) + "M6";
						CpitchN = 21 + startingKey;

					} else if (rand1 >= 0) {
						CpitchS = (lastOctave + 2) + "R";
						CpitchN = 24 + startingKey;

					}

					// ////////////////////////////////////////

					String Cpitch = CpitchS;

					PitchArray.add(Cpitch);
					PitchNumberArray.add(CpitchN);

					double CR = 0.0;
					double rand3 = rand.nextInt(100) ; // (0, 1, 2, or 3)//RHYTHMIC VALUE					// For velocity, some of the values will go from 54-64, and
					
					if (rand3 >= 97) {
						CR = 1.0;
						
					} else if (rand3 >= 90) {
						CR = 0.5;
					} else if (rand3 >= 30) {
						CR = 0.25;
					} else if (rand3 >= 0) {
						CR = 0.125;
						
					}
					

					if (CR == .125) {
						numberof8thnotes++;
					}

					RhythmArray.add(CR);
					// At this point, the (i - 1)
					// element
					// should be filled.

					// System.out.println(Cpitch + " " + CR + " " + Cvelocity);

					i++;

					// The entire rhythmic placing system is based on layers:
					if (CR == 1.0) {
						bitcount += 240;

					} else if (CR == .5) {
						bitcount += 120;

					} else if (CR == .25) {
						bitcount += 60;

					} else if (CR == .125) {
						bitcount += 30;

					}
					BitCountArray.add(bitcount); // ith element is filled

					if (bitcount == 240) {
						bitcount = bitcount - 240;

					} else if (bitcount > 240 ) {
						
						System.out.println("PROBLEM HERE");
					}

					// Now that bitcount is set, we can set Beat:
					 if (BitCountArray.get(i) == 240) {

						intermeasurecount = 1;
					} else if (BitCountArray.get(i) >= 180) {

						intermeasurecount = 4;
					} else if (BitCountArray.get(i) >= 120) {

						intermeasurecount = 3;
					} else if (BitCountArray.get(i)>= 60) {

						intermeasurecount = 2;
					} else if (BitCountArray.get(i)>= 0) {

						intermeasurecount = 1;

					}
					InterMeasureCountArray.add(intermeasurecount); // ith
																	// element
																	// is
																	// filled

					// FOR NOW THIS ONLY WORKS BECAUSE THERE's NO POSSIBILITY
					// FOR
					// THE
					// NEW ADDITION TO EXCEED A HALF NOTE.
					if (InterMeasureCountArray.get( (i-1) ) > InterMeasureCountArray
							.get(i)) {
						extrameasurecount++;
					}
					if (extrameasurecount > 4) {
						extrameasurecount = extrameasurecount - 4;
					}
					if (BitCountArray.get(i) == 240) {
						extrameasurecount++;	
					}
					

					ExtraMeasureCountArray.add(extrameasurecount);

					if (ExtraMeasureCountArray.get( (i-1) ) > ExtraMeasureCountArray
							.get(i)) {
						phrasecount++;
					}
					

					PhraseCountArray.add(phrasecount);// 0th element is filled,
														// 1st element is
														// partially filled.

					// ////////////////////////////////////////////////////////////////

				} // i = 0 has now ended. The first random note has been
					// generated. The following responds to the past.

				// 111111111111111111111111111111111111111111111111111111111111111111111111111111

				else if (i == 1) {

					// Laws: Velocity can be set first, because it follows the
					// most simple rules, which have little to do with the past.

					Random rand = new Random();

					// For velocity, all values on 2 and 4 will be stronger than
					// those on any other beat. All non-beat notes will be a bit
					// softer.

					String CpitchS = null;
					int CpitchN = 0;


//						//I Major Pentatonic

					
					if (PitchNumberArray.get( (i-1) ) == 24 + startingKey) {

						int rand1 = rand.nextInt(200);

						if (rand1 >= 160) {

							CpitchS = "5R";
							CpitchN = 24 + startingKey;

						} else if (rand1 >= 120) {

							CpitchS = "4M6";
							CpitchN = 21 + startingKey;

						} else if (rand1 >= 80) {

							CpitchS = "4P5";
							CpitchN = 19 + startingKey;

						} else if (rand1 >= 30) {

							CpitchS = "4M3";
							CpitchN = 16 + startingKey;

						} else if (rand1 >= 0) {

							CpitchS = "4R";
							CpitchN = 12 + startingKey;

						} 

						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was the highest has just
						// ended.

					else if (PitchNumberArray.get( (i-1) ) == 21 + startingKey) {
						int rand1 = rand.nextInt(200);
						// rand3 is either eighth note, quarter note, or
						// whole note.
						/*if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
							int randT = rand.nextInt(2);

							if (randT == 0) {
								CpitchS = "4P5";
								CpitchN = 19 + startingKey;
							} else if (randT == 1) {
								CpitchS = "4M3";
								CpitchN = 16 + startingKey;
							}
						} else if (PitchNumberArray.get(i - 2) == 17 + startingKey) {

							CpitchS = "4P5";
							CpitchN = 19 + startingKey;
						} else {
*/
							if (rand1 >= 130) {

								CpitchS = "5R";
								CpitchN = 24 + startingKey;

							} else if (rand1 >= 100) {

								CpitchS = "4M6";
								CpitchN = 21 + startingKey;

							} else if (rand1 >= 30) {

								CpitchS = "4P5";
								CpitchN = 19 + startingKey;

							} else if (rand1 >= 20) {

								CpitchS = "4M3";
								CpitchN = 16 + startingKey;

							} else if (rand1 >= 0) {

								CpitchS = "4R";
								CpitchN = 12 + startingKey;

							}
						

						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was 4M6 has just ended.

					else if (PitchNumberArray.get( (i-1) ) == 19 + startingKey) {
						int rand1 = rand.nextInt(200);
						// rand3 is either eighth note, quarter note, or
						// whole note.
						if (rand1 >= 180) {

							CpitchS = "5R";
							CpitchN = 24 + startingKey;

						} else if (rand1 >= 140) {

							CpitchS = "4M6";
							CpitchN = 21 + startingKey;

						} else if (rand1 >= 120) {

							CpitchS = "4P5";
							CpitchN = 19 + startingKey;

						} else if (rand1 >= 60) {

							CpitchS = "4M3";
							CpitchN = 16 + startingKey;

						} else if (rand1 >= 50) {

							CpitchS = "4M2";
							CpitchN = 14 + startingKey;

						} else if (rand1 >= 40) {

							CpitchS = "4R";
							CpitchN = 12 + startingKey;

						} else if (rand1 >= 30) {

							CpitchS = "3P5";
							CpitchN = 7 + startingKey;

						} else if (rand1 >= 0) {

							CpitchS = "4P4";
							CpitchN = 17 + startingKey;

						}
						//} (closes the else)
						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was 4P5 has just ended.
					else if (PitchNumberArray.get( (i-1) ) == 17 + startingKey) {
						int rand1 = rand.nextInt(200);
						// rand3 is either eighth note, quarter note, or
						// whole note.
						
						/*
						if (PitchNumberArray.get(i - 2) == 24 + startingKey) {
							int randT = rand.nextInt(2);

							if (randT == 0) {
								CpitchS = "4P5";
								CpitchN = 19 + startingKey;
							} else if (randT == 1) {
								CpitchS = "4M3";
								CpitchN = 16 + startingKey;
							}
						} else if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
							int randT = rand.nextInt(2);

							if (randT == 0) {
								CpitchS = "4P5";
								CpitchN = 19 + startingKey;
							} else if (randT == 1) {
								CpitchS = "4M3";
								CpitchN = 16 + startingKey;
							}
						} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
							
								CpitchS = "4M3";
								CpitchN = 16 + startingKey;
							
						} else if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
							
							CpitchS = "4M3";
							CpitchN = 16 + startingKey;
						
					} else { */
							if (rand1 >= 170) {

								CpitchS = "5R";
								CpitchN = 24 + startingKey;

							} else if (rand1 >= 150) {

								CpitchS = "4M6";
								CpitchN = 21 + startingKey;

							} else if (rand1 >= 100) {

								CpitchS = "4P5";
								CpitchN = 19 + startingKey;

							} else if (rand1 >= 95) {

								CpitchS = "4P4";
								CpitchN = 17 + startingKey;

							} else if (rand1 >= 50) {

								CpitchS = "4M3";
								CpitchN = 16 + startingKey;

							} else if (rand1 >= 30) {

								CpitchS = "4M2";
								CpitchN = 14 + startingKey;

							} else if (rand1 >= 20) {

								CpitchS = "4R";
								CpitchN = 12 + startingKey;

							} else if (rand1 >= 5) {

								CpitchS = "3M6";
								CpitchN = 9 + startingKey;

							} else if (rand1 >= 0) {

								CpitchS = "3P5";
								CpitchN = 7 + startingKey;

							}

						//}

						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);
					} // If the last note was 4P4 has just ended.
					else if (PitchNumberArray.get( (i-1) ) == 16 + startingKey) {
						int rand1 = rand.nextInt(200);
						// rand3 is either eighth note, quarter note, or
						// whole note.

						if (rand1 >= 190) {

							CpitchS = "5R";
							CpitchN = 24 + startingKey;

						} else if (rand1 >= 175) {

							CpitchS = "4M6";
							CpitchN = 21 + startingKey;

						} else if (rand1 >= 125) {

							CpitchS = "4P5";
							CpitchN = 19 + startingKey;

						} else if (rand1 >= 100) {

							CpitchS = "4P4";
							CpitchN = 17 + startingKey;

						} else if (rand1 >= 80) {

							CpitchS = "4M3";
							CpitchN = 16 + startingKey;

						} else if (rand1 >= 70) {

							CpitchS = "4M2";
							CpitchN = 14 + startingKey;

						} else if (rand1 >= 30) {

							CpitchS = "4R";
							CpitchN = 12 + startingKey;

						} else if (rand1 >= 20) {

							CpitchS = "3M6";
							CpitchN = 9 + startingKey;

						} else if (rand1 >= 0) {

							CpitchS = "3P5";
							CpitchN = 7 + startingKey;

						}

						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);
					} // If the last note was 4M3 has just ended.
					else if (PitchNumberArray.get( (i-1) ) == 14 + startingKey) {
						int rand1 = rand.nextInt(200);
						// rand3 is either eighth note, quarter note, or
						// whole note.

						/*
						if (PitchNumberArray.get(i - 2) == 24 + startingKey) {

							CpitchS = "4M3";
							CpitchN = 16 + startingKey;

						} else if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
							int randT = rand.nextInt(2);

							if (randT == 0) {
								CpitchS = "4R";
								CpitchN = 12 + startingKey;
							} else if (randT == 1) {
								CpitchS = "4M3";
								CpitchN = 16 + startingKey;
							}
						} else if (PitchNumberArray.get(i - 2) == 16 + startingKey) {
							int randT = rand.nextInt(2);

							if (randT == 0) {
								CpitchS = "4R";
								CpitchN = 12 + startingKey;
							} else if (randT == 1) {
								CpitchS = "4M3";
								CpitchN = 16 + startingKey;
							}
						} else if (PitchNumberArray.get(i - 2) == 17 + startingKey) {

							CpitchS = "4M3";
							CpitchN = 16 + startingKey;

						} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
							int randT = rand.nextInt(2);
							
							if (randT == 0) {
								CpitchS = "4R";
								CpitchN = 12 + startingKey;
							} else if (randT == 1) {
								CpitchS = "4M3";
								CpitchN = 16 + startingKey;
							}
							

						} else if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
							int randT = rand.nextInt(2);
							if (randT == 0) {
								CpitchS = "4R";
								CpitchN = 12 + startingKey;
							} else if (randT == 1) {
								CpitchS = "4M3";
								CpitchN = 16 + startingKey;
							}
						
					}     else { */

							if (rand1 >= 195) {

								CpitchS = "5R";
								CpitchN = 24 + startingKey;

							} else if (rand1 >= 185) {

								CpitchS = "4M6";
								CpitchN = 21 + startingKey;

							} else if (rand1 >= 150) {

								CpitchS = "4P5";
								CpitchN = 19 + startingKey;

							} else if (rand1 >= 120) {

								CpitchS = "4P4";
								CpitchN = 17 + startingKey;

							} else if (rand1 >= 60) {

								CpitchS = "4M3";
								CpitchN = 16 + startingKey;

							} else if (rand1 >= 50) {

								CpitchS = "4M2";
								CpitchN = 14 + startingKey;

							} else if (rand1 >= 10) {

								CpitchS = "4R";
								CpitchN = 12 + startingKey;

							} else if (rand1 >= 5) {

								CpitchS = "3M6";
								CpitchN = 9 + startingKey;

							} else if (rand1 >= 0) {

								CpitchS = "3P5";
								CpitchN = 7 + startingKey;

							}
						//}
						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was 4M2 has just ended.
					else if (PitchNumberArray.get( (i-1) ) == 12 + startingKey) {
						int rand1 = rand.nextInt(250);
						// rand3 is either eighth note, quarter note, or
						// whole note.
						/*
						if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
							int randT = rand.nextInt(2);
							if (randT == 0) {
								CpitchS = "3P5";
								CpitchN = 7 + startingKey;
							} else if (randT == 1) {
								CpitchS = "3M3";
								CpitchN = 4 + startingKey;
							}
							

						} else {*/
						
						if (rand1 >= 235) {

							CpitchS = "5R";
							CpitchN = 24 + startingKey;

						} else if (rand1 >= 220) {

							CpitchS = "4M6";
							CpitchN = 21 + startingKey;

						} else if (rand1 >= 190) {

							CpitchS = "4P5";
							CpitchN = 19 + startingKey;

						} else if (rand1 >= 170) {

							CpitchS = "4P4";
							CpitchN = 17 + startingKey;

						} else if (rand1 >= 140) {

							CpitchS = "4M3";
							CpitchN = 16 + startingKey;

						} else if (rand1 >= 100) {

							CpitchS = "4M2";
							CpitchN = 14 + startingKey;

						} else if (rand1 >= 60) {

							CpitchS = "4R";
							CpitchN = 12 + startingKey;

						} else if (rand1 >= 30) {

							CpitchS = "3M6";
							CpitchN = 9 + startingKey;

						} else if (rand1 >= 0) {

							CpitchS = "3P5";
							CpitchN = 7 + startingKey;

						}
						//}
						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was 4R has just ended.
					
					else if (PitchNumberArray.get( (i-1) ) == 9 + startingKey) {
						int rand1 = rand.nextInt(200);
						// rand3 is either eighth note, quarter note, or
						// whole note.
						/*
						if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
							int randT = rand.nextInt(2);

							if (randT == 0) {
								CpitchS = "3P5";
								CpitchN = 7 + startingKey;
							} else if (randT == 1) {
								CpitchS = "4R";
								CpitchN = 12 + startingKey;
							}
						} else if (PitchNumberArray.get(i - 2) == 2 + startingKey) {
							
								CpitchS = "3P5";
								CpitchN = 7 + startingKey;
							
						} else {*/
							if (rand1 >= 195) {

								CpitchS = "4M6";
								CpitchN = 21 + startingKey;

							} else if (rand1 >= 190) {

								CpitchS = "4P5";
								CpitchN = 19 + startingKey;

							} else if (rand1 >= 180) {

								CpitchS = "4P4";
								CpitchN = 17 + startingKey;

							} else if (rand1 >= 160) {

								CpitchS = "4M3";
								CpitchN = 16 + startingKey;

							} else if (rand1 >= 130) {

								CpitchS = "4M2";
								CpitchN = 14 + startingKey;

							} else if (rand1 >= 80) {

								CpitchS = "4R";
								CpitchN = 12 + startingKey;

							} else if (rand1 >= 50) {

								CpitchS = "3M6";
								CpitchN = 9 + startingKey;

							} else if (rand1 >= 20) {

								CpitchS = "3P5";
								CpitchN = 7 + startingKey;

							} else if (rand1 >= 15) {

								CpitchS = "3P4";
								CpitchN = 5 + startingKey;

							} else if (rand1 >= 5) {

								CpitchS = "3M3";
								CpitchN = 4 + startingKey;

							} else if (rand1 >= 0) {

								CpitchS = "3R";
								CpitchN = startingKey;

							}

						//}
						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was 3M6 has just ended.
					else if (PitchNumberArray.get( (i-1) ) == 7 + startingKey) {
						
						/*
						if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) > 0) {*/
							int rand1 = rand.nextInt(200);
						if (rand1 >= 190) {

							CpitchS = "4P5";
							CpitchN = 19 + startingKey;

						} else if (rand1 >= 185) {

							CpitchS = "4P4";
							CpitchN = 17 + startingKey;

						} else if (rand1 >= 150) {

							CpitchS = "4M3";
							CpitchN = 16 + startingKey;

						} else if (rand1 >= 130) {

							CpitchS = "4M2";
							CpitchN = 14 + startingKey;

						} else if (rand1 >= 110) {

							CpitchS = "4R";
							CpitchN = 12 + startingKey;

						} else if (rand1 >= 80) {

							CpitchS = "3M6";
							CpitchN = 9 + startingKey;

						} else if (rand1 >= 50) {

							CpitchS = "3P5";
							CpitchN = 7 + startingKey;

						} else if (rand1 >= 30) {

							CpitchS = "3P4";
							CpitchN = 5 + startingKey;

						} else if (rand1 >= 10) {

							CpitchS = "3M3";
							CpitchN = 4 + startingKey;

						} else if (rand1 >= 5) {

							CpitchS = "3M2";
							CpitchN = 2 + startingKey;

						} else if (rand1 >= 0) {

							CpitchS = "3R";
							CpitchN = startingKey;

						}
						/*else if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) <= 0) {*/
						/*	int randMist = rand.nextInt(120);
							if (randMist >= 110) {

								CpitchS = "4R";
								CpitchN = 12 + startingKey;

							} else if (randMist >= 80) {

								CpitchS = "3M6";
								CpitchN = 9 + startingKey;

							} else if (randMist >= 50) {

								CpitchS = "3P5";
								CpitchN = 7 + startingKey;

							} else if (randMist >= 40) {

								CpitchS = "3P4";
								CpitchN = 5 + startingKey;

							} else if (randMist >= 10) {

								CpitchS = "3M3";
								CpitchN = 4 + startingKey;

							} else if (randMist >= 5) {

								CpitchS = "3M2";
								CpitchN = 2 + startingKey;

							} else if (randMist >= 0) {

								CpitchS = "3R";
								CpitchN = startingKey;

							}
						
						
						//}
						*/
						
						
						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was 3P5 has just ended.
					else if (PitchNumberArray.get( (i-1) ) == 5 + startingKey) {
						int rand1 = rand.nextInt(200);
						/*
						if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
							int randT = rand.nextInt(10);

							if (randT >=9 ) {
								CpitchS = "4R";
								CpitchN = 12 + startingKey;
							} else if (randT >= 6) {
								CpitchS = "3P5";
								CpitchN = 7 + startingKey;
							} else if (randT >= 0) {
								CpitchS = "3M3";
								CpitchN = 4 + startingKey;
							}
						} else if (PitchNumberArray.get(i - 2) == startingKey) {
							int randT = rand.nextInt(30);
							if (randT >= 25) {
								CpitchS = "3M6";
								CpitchN = 9 + startingKey;
							} else if (randT >= 15) {
								CpitchS = "3P5";
								CpitchN = 7 + startingKey;
							} else if (randT >= 0) {
								CpitchS = "3M3";
								CpitchN = 4 + startingKey;
							}
						} else {*/

						if (rand1 >= 190) {

							CpitchS = "4R";
							CpitchN = 12 + startingKey;

						} else if (rand1 >= 180) {

							CpitchS = "3M6";
							CpitchN = 9 + startingKey;

						} else if (rand1 >= 140) {

							CpitchS = "3P5";
							CpitchN = 7 + startingKey;

						} else if (rand1 >= 130) {

							CpitchS = "3P4";
							CpitchN = 5 + startingKey;

						} else if (rand1 >= 40) {

							CpitchS = "3M3";
							CpitchN = 4 + startingKey;

						} else if (rand1 >= 10) {

							CpitchS = "3M2";
							CpitchN = 2 + startingKey;

						} else if (rand1 >= 0) {

							CpitchS = "3R";
							CpitchN = startingKey;

						}
						//}
						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was 3P4 has just ended.
					else if (PitchNumberArray.get( (i-1) ) == 4 + startingKey) {
						
						/*if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) > 0) {*/
							int rand1 = rand.nextInt(200);
						if (rand1 >= 190) {

							CpitchS = "4M3";
							CpitchN = 16 + startingKey;

						} else if (rand1 >= 170) {

							CpitchS = "4R";
							CpitchN = 12 + startingKey;

						} else if (rand1 >= 150) {

							CpitchS = "3M6";
							CpitchN = 9 + startingKey;

						} else if (rand1 >= 110) {

							CpitchS = "3P5";
							CpitchN = 7 + startingKey;

						} else if (rand1 >= 80) {

							CpitchS = "3P4";
							CpitchN = 5 + startingKey;

						} else if (rand1 >= 60) {

							CpitchS = "3M3";
							CpitchN = 4 + startingKey;

						} else if (rand1 >= 30) {

							CpitchS = "3M2";
							CpitchN = 2 + startingKey;

						} else if (rand1 >= 0) {

							CpitchS = "3R";
							CpitchN = startingKey;

						}
						//} else if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) <= 0) {
							/*int randMist = rand.nextInt(150);
							if (randMist >= 130) {

								CpitchS = "4R";
								CpitchN = 12 + startingKey;

							} else if (randMist >= 100) {

								CpitchS = "3M6";
								CpitchN = 9 + startingKey;

							} else if (randMist >= 80) {

								CpitchS = "3P5";
								CpitchN = 7 + startingKey;

							} else if (randMist >= 60) {

								CpitchS = "3P4";
								CpitchN = 5 + startingKey;

							} else if (randMist >= 40) {

								CpitchS = "3M3";
								CpitchN = 4 + startingKey;

							} else if (randMist >= 20) {

								CpitchS = "3M2";
								CpitchN = 2 + startingKey;

							} else if (randMist >= 0) {

								CpitchS = "3R";
								CpitchN = startingKey;

							}
						//}
						*/
						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was 3M3 has just ended.
					else if (PitchNumberArray.get( (i-1) ) == 2 + startingKey) {
						int rand1 = rand.nextInt(200);
/*
						if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
							int randT = rand.nextInt(2);

							if (randT == 0) {
								CpitchS = "3M3";
								CpitchN = 4 + startingKey;
							} else if (randT == 1) {
								CpitchS = "3R";
								CpitchN = startingKey;
							}
						} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
							int randT = rand.nextInt(2);

							if (randT == 0) {
								CpitchS = "3M3";
								CpitchN = 4 + startingKey;
							} else if (randT == 1) {
								CpitchS = "3R";
								CpitchN = startingKey;
							}
						} else if (PitchNumberArray.get(i - 2) == 12 + startingKey) {
							int randT = rand.nextInt(2);

							if (randT == 0) {
								CpitchS = "3M3";
								CpitchN = 4 + startingKey;
							} else if (randT == 1) {
								CpitchS = "3R";
								CpitchN = startingKey;
							}
						} else {*/
						
						if (rand1 >= 190) {

							CpitchS = "3M6";
							CpitchN = 9 + startingKey;

						} else if (rand1 >= 160) {

							CpitchS = "3P5";
							CpitchN = 7 + startingKey;

						} else if (rand1 >= 150) {

							CpitchS = "3P4";
							CpitchN = 5 + startingKey;

						} else if (rand1 >= 100) {

							CpitchS = "3M3";
							CpitchN = 4 + startingKey;

						} else if (rand1 >= 90) {

							CpitchS = "3M2";
							CpitchN = 2 + startingKey;

						} else if (rand1 >= 0) {

							CpitchS = "3R";
							CpitchN = startingKey;

						}
						//}
						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was 3M2 has just ended.

					else if (PitchNumberArray.get( (i-1) ) == startingKey) {
						int rand1 = rand.nextInt(7);

						if (rand1 == 0) {

							CpitchS = "3M6";
							CpitchN = 9 + startingKey;

						} else if (rand1 == 1) {

							CpitchS = "3P5";
							CpitchN = 7 + startingKey;

						} else if (rand1 == 2) {

							CpitchS = "3P4";
							CpitchN = 5 + startingKey;

						} else if (rand1 == 3) {

							CpitchS = "3M3";
							CpitchN = 4 + startingKey;

						} else if (rand1 == 4) {

							CpitchS = "3M2";
							CpitchN = 2 + startingKey;

						} else if (rand1 == 5) {

							CpitchS = "3R";
							CpitchN = startingKey;

						} else if (rand1 == 6) {

							CpitchS = "4R";
							CpitchN = 12 + startingKey;

						}
						PitchArray.add(CpitchS);
						PitchNumberArray.add(CpitchN);

					} // If the last note was 3R has just ended.

				

						

						

					
						// //////////////////////////////////////////////////////////////////////1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1

					// PitchArray and PitchNumberArray should have been decided
					// so far.

					// VELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENT

					if (bitcount == 30) {

						int rand4 = rand.nextInt(9);

						int Cvelocity = 40 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 60) {

						int rand4 = rand.nextInt(9);

						int Cvelocity = 81 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 90) {

						int rand4 = rand.nextInt(9);

						int Cvelocity = 40 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 150) {

						int rand4 = rand.nextInt(9);

						int Cvelocity = 40 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 180) {
						int rand4 = rand.nextInt(9);

						int Cvelocity = 81 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 210) {

						int rand4 = rand.nextInt(9);

						int Cvelocity = 40 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 0) {
						int rand4 = rand.nextInt(9);
						int Cvelocity = 55 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 120) {
						int rand4 = rand.nextInt(9);
						int Cvelocity = 55 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 240) {
						int rand4 = rand.nextInt(9);
						int Cvelocity = 55 + rand4;
						VelocityArray.add(Cvelocity); // At this point, the
														// (ith)
						// element
						// should be filled.

					}
					// VELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENT

					//RHYTHM STATEMENT START RHYTHMSTATEMENTSTART
					
					//double rand3 = rand.nextInt(4); // (0, 1, 2, or 3)//RHYTHMIC VALUE

					double CRt = 0.0;
					if(bitcount == 0) {
						double rand3 = rand.nextInt(30);
						if (rand3 >= 28) {
							CRt = 1.0;
						} else if (rand3 >=23 ) {
							CRt = .5;
						} else if (rand3 >=10 ) {
							CRt = .25;
						} else if (rand3 >= 0 ) {
							CRt = .125;
						}
						
					} else if (bitcount == 30) {
						
						CRt = (.125);
						
					} else if (bitcount == 60) {
						double rand3 = rand.nextInt(30);
						 if (rand3 >=28 ) {
							CRt = .5;
						} else if (rand3 >=10 ) {
							CRt = .25;
						} else if (rand3 >= 0 ) {
							CRt = .125;
						}
						
					} else if (bitcount == 90) {
						 CRt = (.125);
						
					} else if (bitcount == 120) {
						double rand3 = rand.nextInt(30);
						 if (rand3 >=28 ) {
							CRt = .5;
						} else if (rand3 >=10 ) {
							CRt = .25;
						} else if (rand3 >= 0 ) {
							CRt = .125;
						}
						
					} else if (bitcount == 150) {
						 CRt = (.125);
						
					} else if (bitcount == 180) {
						double rand3 = rand.nextInt(2);
						
						if (rand3 == 0) {
							
						CRt = (.25);
						} else if (rand3 == 1) {
							
						CRt = (.125);
								
						}
						
					} else if (bitcount == 210) {
					 CRt = (.125);
						
					} else {
						System.out.println("Problem Here Rhythm");	
						 CRt = (.125);
					}
					
					double CR = CRt;
					
					RhythmArray.add(CR);
					
					if (CR == .125) {
						numberof8thnotes++;
					}

				
					
					// RhythmEnd

					// System.out.println(Cpitch + " " + CR + " " + Cvelocity);



					i++;

					// The entire rhythmic placing system is based on layers:
					if (CR == 1.0) {
						bitcount += 240;

					} else if (CR == .5) {
						bitcount += 120;

					} else if (CR == .25) {
						bitcount += 60;

					} else if (CR == .125) {
						bitcount += 30;

					}
					BitCountArray.add(bitcount); // ith element is filled

					if (bitcount == 240) {
						bitcount = bitcount - 240;

					} else if (bitcount > 240 ) {
						
						System.out.println("PROBLEM HERE");
					}

					// Now that bitcount is set, we can set Beat:
					 if (BitCountArray.get(i) == 240) {

						intermeasurecount = 1;
					
					 } else if (BitCountArray.get(i) >= 180) {

						intermeasurecount = 4;
					} else if (BitCountArray.get(i) >= 120) {

						intermeasurecount = 3;
					} else if (BitCountArray.get(i)>= 60) {

						intermeasurecount = 2;
					} else if (BitCountArray.get(i)>= 0) {

						intermeasurecount = 1;

					}
					InterMeasureCountArray.add(intermeasurecount); // ith
																	// element
																	// is
																	// filled

					// FOR NOW THIS ONLY WORKS BECAUSE THERE's NO POSSIBILITY
					// FOR
					// THE
					// NEW ADDITION TO EXCEED A HALF NOTE.
					if (BitCountArray.get(i) - BitCountArray.get(i-1) == 0) {

						extrameasurecount++;
					} 
					if (InterMeasureCountArray.get( (i-1) ) > InterMeasureCountArray
							.get(i)) {
						extrameasurecount++;
					}
					 if (extrameasurecount > 4) {
						extrameasurecount = extrameasurecount - 4;
					}
					
					

					ExtraMeasureCountArray.add(extrameasurecount);

					if (ExtraMeasureCountArray.get( (i-1) ) > ExtraMeasureCountArray
							.get(i)) {
						phrasecount++;
					}
					

					PhraseCountArray.add(phrasecount);// 0th element is filled,
														// 1st element is
														// partially filled.

					// ////////////////////////////////////////////////////////////////
				}	else {

					Random rand = new Random();

					String CpitchS = null;
					int CpitchN = 0;

					if (PhraseCountArray.get(i) >= 1) {

						// First Phrase, First Measure
						if (ExtraMeasureCountArray.get(i) == 1) {
// 							//I Major Pentatonic

							
							if (PitchNumberArray.get( (i-1) ) == 24 + startingKey) {

								int rand1 = rand.nextInt(200);

								if (rand1 >= 160) {

									CpitchS = "5R";
									CpitchN = 24 + startingKey;

								} else if (rand1 >= 120) {

									CpitchS = "4M6";
									CpitchN = 21 + startingKey;

								} else if (rand1 >= 80) {

									CpitchS = "4P5";
									CpitchN = 19 + startingKey;

								} else if (rand1 >= 30) {

									CpitchS = "4M3";
									CpitchN = 16 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} 
								 

								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} else if (PitchNumberArray.get( (i-1) ) == 23 + startingKey) {
								if (PitchNumberArray.get(i - 2) == 24 + startingKey) {
									

									
										CpitchS = "4M6";
										CpitchN = 21 + startingKey;
									
								} else if (PitchNumberArray.get(i - 2) == 21 + startingKey) {

									CpitchS = "5R";
									CpitchN = 24 + startingKey;
								} else {
									
									CpitchS = "5R";
									CpitchN = 24 + startingKey;
									
								}
								
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);
								
							}

							else if (PitchNumberArray.get( (i-1) ) == 21 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.
								if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "4P5";
										CpitchN = 19 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4M3";
										CpitchN = 16 + startingKey;
									}
								} else if (PitchNumberArray.get(i - 2) == 17 + startingKey) {

									CpitchS = "4P5";
									CpitchN = 19 + startingKey;
								} else {

									if (rand1 >= 130) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									}
								}

								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 4M6 has just ended.

							else if (PitchNumberArray.get( (i-1) ) == 19 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.
								if (rand1 >= 180) {

									CpitchS = "5R";
									CpitchN = 24 + startingKey;

								} else if (rand1 >= 140) {

									CpitchS = "4M6";
									CpitchN = 21 + startingKey;

								} else if (rand1 >= 120) {

									CpitchS = "4P5";
									CpitchN = 19 + startingKey;

								} else if (rand1 >= 60) {

									CpitchS = "4M3";
									CpitchN = 16 + startingKey;

								} else if (rand1 >= 50) {

									CpitchS = "4M2";
									CpitchN = 14 + startingKey;

								} else if (rand1 >= 40) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 >= 30) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "4P4";
									CpitchN = 17 + startingKey;

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 4P5 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 17 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.
								if (PitchNumberArray.get(i - 2) == 24 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "4P5";
										CpitchN = 19 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4M3";
										CpitchN = 16 + startingKey;
									}
								} else if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "4P5";
										CpitchN = 19 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4M3";
										CpitchN = 16 + startingKey;
									}
								} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
									
										CpitchS = "4M3";
										CpitchN = 16 + startingKey;
									
								} else if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
									
									CpitchS = "4M3";
									CpitchN = 16 + startingKey;
								
							} else {
									if (rand1 >= 170) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 150) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 95) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									}

								}

								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);
							} // If the last note was 4P4 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 16 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.

								if (rand1 >= 190) {

									CpitchS = "5R";
									CpitchN = 24 + startingKey;

								} else if (rand1 >= 175) {

									CpitchS = "4M6";
									CpitchN = 21 + startingKey;

								} else if (rand1 >= 125) {

									CpitchS = "4P5";
									CpitchN = 19 + startingKey;

								} else if (rand1 >= 100) {

									CpitchS = "4P4";
									CpitchN = 17 + startingKey;

								} else if (rand1 >= 80) {

									CpitchS = "4M3";
									CpitchN = 16 + startingKey;

								} else if (rand1 >= 70) {

									CpitchS = "4M2";
									CpitchN = 14 + startingKey;

								} else if (rand1 >= 30) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 >= 20) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								}

								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);
							} // If the last note was 4M3 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 14 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.

								if (PitchNumberArray.get(i - 2) == 24 + startingKey) {

									CpitchS = "4M3";
									CpitchN = 16 + startingKey;

								} else if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "4R";
										CpitchN = 12 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4M3";
										CpitchN = 16 + startingKey;
									}
								} else if (PitchNumberArray.get(i - 2) == 16 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "4R";
										CpitchN = 12 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4M3";
										CpitchN = 16 + startingKey;
									}
								} else if (PitchNumberArray.get(i - 2) == 17 + startingKey) {

									CpitchS = "4M3";
									CpitchN = 16 + startingKey;

								} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
									int randT = rand.nextInt(2);
									
									if (randT == 0) {
										CpitchS = "4R";
										CpitchN = 12 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4M3";
										CpitchN = 16 + startingKey;
									}
									

								} else if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
									int randT = rand.nextInt(2);
									if (randT == 0) {
										CpitchS = "4R";
										CpitchN = 12 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4M3";
										CpitchN = 16 + startingKey;
									}
								
							}     else {

									if (rand1 >= 195) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 185) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 150) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 60) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									}
								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 4M2 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 12 + startingKey) {
								int rand1 = rand.nextInt(250);
								// rand3 is either eighth note, quarter note, or
								// whole note.
								if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
									int randT = rand.nextInt(2);
									if (randT == 0) {
										CpitchS = "3P5";
										CpitchN = 7 + startingKey;
									} else if (randT == 1) {
										CpitchS = "3M3";
										CpitchN = 4 + startingKey;
									}
									

								} else {
								
								if (rand1 >= 235) {

									CpitchS = "5R";
									CpitchN = 24 + startingKey;

								} else if (rand1 >= 220) {

									CpitchS = "4M6";
									CpitchN = 21 + startingKey;

								} else if (rand1 >= 190) {

									CpitchS = "4P5";
									CpitchN = 19 + startingKey;

								} else if (rand1 >= 170) {

									CpitchS = "4P4";
									CpitchN = 17 + startingKey;

								} else if (rand1 >= 140) {

									CpitchS = "4M3";
									CpitchN = 16 + startingKey;

								} else if (rand1 >= 100) {

									CpitchS = "4M2";
									CpitchN = 14 + startingKey;

								} else if (rand1 >= 60) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 >= 30) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								}
								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 4R has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 11 + startingKey) {
								if (PitchNumberArray.get(i - 2) == 12 + startingKey) {
									

									
									CpitchS = "3M6";
									CpitchN = 9 + startingKey;
								
							} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {

								CpitchS = "4R";
								CpitchN = 12 + startingKey;
							} else {
								
								CpitchS = "4R";
								CpitchN = 12 + startingKey;
								
							}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);
								
							}
							else if (PitchNumberArray.get( (i-1) ) == 9 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.
								if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "3P5";
										CpitchN = 7 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4R";
										CpitchN = 12 + startingKey;
									}
								} else if (PitchNumberArray.get(i - 2) == 2 + startingKey) {
									
										CpitchS = "3P5";
										CpitchN = 7 + startingKey;
									
								} else {
									if (rand1 >= 195) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 190) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 180) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 160) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 130) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 15) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 3M6 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 7 + startingKey) {
								
								
								if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) > 0) {
									int rand1 = rand.nextInt(200);
								if (rand1 >= 190) {

									CpitchS = "4P5";
									CpitchN = 19 + startingKey;

								} else if (rand1 >= 185) {

									CpitchS = "4P4";
									CpitchN = 17 + startingKey;

								} else if (rand1 >= 150) {

									CpitchS = "4M3";
									CpitchN = 16 + startingKey;

								} else if (rand1 >= 130) {

									CpitchS = "4M2";
									CpitchN = 14 + startingKey;

								} else if (rand1 >= 110) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 >= 80) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 >= 50) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 >= 30) {

									CpitchS = "3P4";
									CpitchN = 5 + startingKey;

								} else if (rand1 >= 10) {

									CpitchS = "3M3";
									CpitchN = 4 + startingKey;

								} else if (rand1 >= 5) {

									CpitchS = "3M2";
									CpitchN = 2 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "3R";
									CpitchN = startingKey;

								}
								} else if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) <= 0) {
									int rand1 = rand.nextInt(120);
									if (rand1 >= 110) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}
								
								
								}
								
								
								
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 3P5 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 5 + startingKey) {
								int rand1 = rand.nextInt(200);
								
								if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
									int randT = rand.nextInt(10);

									if (randT >=9 ) {
										CpitchS = "4R";
										CpitchN = 12 + startingKey;
									} else if (randT >= 6) {
										CpitchS = "3P5";
										CpitchN = 7 + startingKey;
									} else if (randT >= 0) {
										CpitchS = "3M3";
										CpitchN = 4 + startingKey;
									}
								} else if (PitchNumberArray.get(i - 2) == startingKey) {
									int randT = rand.nextInt(30);
									if (randT >= 25) {
										CpitchS = "3M6";
										CpitchN = 9 + startingKey;
									} else if (randT >= 15) {
										CpitchS = "3P5";
										CpitchN = 7 + startingKey;
									} else if (randT >= 0) {
										CpitchS = "3M3";
										CpitchN = 4 + startingKey;
									}
								} else {

								if (rand1 >= 190) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 >= 180) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 >= 140) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 >= 130) {

									CpitchS = "3P4";
									CpitchN = 5 + startingKey;

								} else if (rand1 >= 40) {

									CpitchS = "3M3";
									CpitchN = 4 + startingKey;

								} else if (rand1 >= 10) {

									CpitchS = "3M2";
									CpitchN = 2 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "3R";
									CpitchN = startingKey;

								}
								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 3P4 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 4 + startingKey) {
								if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) > 0) {
									int rand1 = rand.nextInt(200);
								if (rand1 >= 190) {

									CpitchS = "4M3";
									CpitchN = 16 + startingKey;

								} else if (rand1 >= 170) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 >= 150) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 >= 110) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 >= 80) {

									CpitchS = "3P4";
									CpitchN = 5 + startingKey;

								} else if (rand1 >= 60) {

									CpitchS = "3M3";
									CpitchN = 4 + startingKey;

								} else if (rand1 >= 30) {

									CpitchS = "3M2";
									CpitchN = 2 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "3R";
									CpitchN = startingKey;

								}
								} else if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) <= 0) {
									int rand1 = rand.nextInt(150);
									if (rand1 >= 130) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 60) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}
								}
								
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 3M3 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 2 + startingKey) {
								int rand1 = rand.nextInt(200);

								if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "3M3";
										CpitchN = 4 + startingKey;
									} else if (randT == 1) {
										CpitchS = "3R";
										CpitchN = startingKey;
									}
								} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "3M3";
										CpitchN = 4 + startingKey;
									} else if (randT == 1) {
										CpitchS = "3R";
										CpitchN = startingKey;
									}
								} else if (PitchNumberArray.get(i - 2) == 12 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "3M3";
										CpitchN = 4 + startingKey;
									} else if (randT == 1) {
										CpitchS = "3R";
										CpitchN = startingKey;
									}
								} else {
								
								if (rand1 >= 190) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 >= 160) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 >= 150) {

									CpitchS = "3P4";
									CpitchN = 5 + startingKey;

								} else if (rand1 >= 100) {

									CpitchS = "3M3";
									CpitchN = 4 + startingKey;

								} else if (rand1 >= 90) {

									CpitchS = "3M2";
									CpitchN = 2 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "3R";
									CpitchN = startingKey;

								}
								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} else if (PitchNumberArray.get( (i-1) ) == startingKey) {
								int rand1 = rand.nextInt(7);

								if (rand1 == 0) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 == 1) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 == 2) {

									CpitchS = "3P4";
									CpitchN = 5 + startingKey;

								} else if (rand1 == 3) {

									CpitchS = "3M3";
									CpitchN = 4 + startingKey;

								} else if (rand1 == 4) {

									CpitchS = "3M2";
									CpitchN = 2 + startingKey;

								} else if (rand1 == 5) {

									CpitchS = "3R";
									CpitchN = startingKey;

								} else if (rand1 == 6) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} else {
								System.out.println("PROBLEM HERE 1!");
							}

						}// ///////////////////Measure 1 just//
							// ended///////////////////////////////////////////////////////111111111111111
						else if (ExtraMeasureCountArray.get(i) == 2) {
							// We are in IV major.

							if (PitchNumberArray.get( (i-1) ) == 24 + startingKey) {

								int rand1 = rand.nextInt(200);

								if (rand1 >= 160) {

									CpitchS = "5R";
									CpitchN = 24 + startingKey;

								} else if (rand1 >= 90) {

									CpitchS = "4M6";
									CpitchN = 21 + startingKey;

								} else if (rand1 >= 80) {

									CpitchS = "4P5";
									CpitchN = 19 + startingKey;

								} else if (rand1 >= 20) {

									CpitchS = "4P4";
									CpitchN = 17 + startingKey;

								} else if (rand1 >= 10) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 5R has just ended
							else if (PitchNumberArray.get( (i-1) ) == 23 + startingKey) {
								
								CpitchS = "5R";
								CpitchN = 24 + startingKey;
								
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);
							}

							else if (PitchNumberArray.get( (i-1) ) == 21 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.
								if (rand1 >= 160) {

									CpitchS = "5R";
									CpitchN = 24 + startingKey;

								} else if (rand1 >= 120) {

									CpitchS = "4M6";
									CpitchN = 21 + startingKey;

								} else if (rand1 >= 80) {

									CpitchS = "4P5";
									CpitchN = 19 + startingKey;

								} else if (rand1 >= 40) {

									CpitchS = "4P4";
									CpitchN = 17 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 4M6 has just ended.

							else if (PitchNumberArray.get( (i-1) ) == 19 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.
								if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "4P4";
										CpitchN = 17 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4M6";
										CpitchN = 21 + startingKey;
									}
								} else if (PitchNumberArray.get(i - 2) == 16 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "4P4";
										CpitchN = 17 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4M6";
										CpitchN = 21 + startingKey;
									}
								} else {

									if (rand1 >= 190) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 110) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 25) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									}
								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 4P5 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 17 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.
								if (rand1 >= 190) {

									CpitchS = "5R";
									CpitchN = 24 + startingKey;

								} else if (rand1 >= 120) {

									CpitchS = "4M6";
									CpitchN = 21 + startingKey;

								} else if (rand1 >= 80) {

									CpitchS = "4P5";
									CpitchN = 19 + startingKey;

								} else if (rand1 >= 50) {

									CpitchS = "4P4";
									CpitchN = 17 + startingKey;

								} else if (rand1 >= 20) {

									CpitchS = "4M2";
									CpitchN = 14 + startingKey;

								} else if (rand1 >= 5) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								}

								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);
							} // If the last note was 4P4 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 16 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.
								if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "4P4";
										CpitchN = 17 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4R";
										CpitchN = 12 + startingKey;
									}
								} else {

									if (rand1 >= 190) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 140) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									}
								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);
							} // If the last note was 4M3 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 14 + startingKey) {
								int rand1 = rand.nextInt(200);

								if (PitchNumberArray.get(i - 2) == 16 + startingKey) {
									int randT = rand.nextInt(10);

									if (randT >= 3) {
										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (randT >= 0) {
										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									}
								} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									
								} else {
									if (rand1 >= 170) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									}
								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 4M2 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 12 + startingKey) {
								int rand1 = rand.nextInt(200);

								if (PitchNumberArray.get(i - 2) == 19 + startingKey) {
									int randT = rand.nextInt(2);

									if (randT == 0) {
										CpitchS = "4P4";
										CpitchN = 17 + startingKey;
									} else if (randT == 1) {
										CpitchS = "4M6";
										CpitchN = 21 + startingKey;
									}
								} else {
									if (rand1 >= 180) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 70) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									}
								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 4R has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 11 + startingKey) {
								
								CpitchS = "4R";
								CpitchN = 12 + startingKey;
								
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);
							}
							else if (PitchNumberArray.get( (i-1) ) == 9 + startingKey) {
								int rand1 = rand.nextInt(200);
								// rand3 is either eighth note, quarter note, or
								// whole note.
								if (rand1 >= 190) {

									CpitchS = "4P4";
									CpitchN = 17 + startingKey;

								} else if (rand1 >= 170) {

									CpitchS = "4M2";
									CpitchN = 14 + startingKey;

								} else if (rand1 >= 150) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 >= 80) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 >= 40) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "3P4";
									CpitchN = 5 + startingKey;

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 3M6 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 7 + startingKey) {
								int rand1 = rand.nextInt(2);

								if (rand1 == 0) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 == 1) {

									CpitchS = "3P4";
									CpitchN = 5 + startingKey;

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 3P5 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 5 + startingKey) {
								int rand1 = rand.nextInt(7);

								if (rand1 == 0) {

									CpitchS = "3P4";
									CpitchN = 17 + startingKey;

								} else if (rand1 == 1) {

									CpitchS = "4M2";
									CpitchN = 14 + startingKey;

								} else if (rand1 == 2) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 == 3) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 == 4) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 == 5) {

									CpitchS = "3M2";
									CpitchN = 2 + startingKey;

								} else if (rand1 == 6) {

									CpitchS = "3R";
									CpitchN = startingKey;

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 3P4 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 4 + startingKey) {
								int rand1 = rand.nextInt(4) + 2;

								if (rand1 == 2) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								} else if (rand1 == 3) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 == 4) {

									CpitchS = "3P4";
									CpitchN = 5 + startingKey;

								} else if (rand1 == 5) {

									CpitchS = "3R";
									CpitchN = startingKey;

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 3M3 has just ended.
							else if (PitchNumberArray.get( (i-1) ) == 2 + startingKey) {
								int rand1 = rand.nextInt(5);

								if (rand1 == 0) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 == 1) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 == 2) {

									CpitchS = "3P4";
									CpitchN = 5 + startingKey;

								} else if (rand1 == 3) {

									CpitchS = "3M2";
									CpitchN = 2 + startingKey;

								} else if (rand1 == 4) {

									CpitchS = "3R";
									CpitchN = startingKey;

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 3M2 has just ended.

							else if (PitchNumberArray.get( (i-1) ) == startingKey) {
								int rand1 = rand.nextInt(6);

								if (rand1 == 0) {

									CpitchS = "3M6";
									CpitchN = 9 + startingKey;

								} else if (rand1 == 1) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 == 2) {

									CpitchS = "3P4";
									CpitchN = 5 + startingKey;

								} else if (rand1 == 3) {

									CpitchS = "3M2";
									CpitchN = 2 + startingKey;

								} else if (rand1 == 4) {

									CpitchS = "3R";
									CpitchN = startingKey;

								} else if (rand1 == 5) {

									CpitchS = "4R";
									CpitchN = 12 + startingKey;

								}
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);

							} // If the last note was 3R has just ended.
							else {
								System.out.println("PROBLEM HERE 2!");
							}

						}// ///////////////Measure 2//
							// ///////////2222222222222222222222222222222222222222222222222222222222222222
						else if (ExtraMeasureCountArray.get(i) == 3) {
// 							
							if (PhraseCountArray.get(i) == 1) {								
								//I Major Pentatonic
							// First Phrase, Last two Measures
							

								
								if (PitchNumberArray.get( (i-1) ) == 24 + startingKey) {

									int rand1 = rand.nextInt(200);

									if (rand1 >= 160) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} 

									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was the highest has just
									// ended.
								else if (PitchNumberArray.get( (i-1) ) == 23 + startingKey) {
									CpitchS = "5R";
									CpitchN = 24 + startingKey;
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
									
								}

								else if (PitchNumberArray.get( (i-1) ) == 21 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter note, or
									// whole note.
									if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "4P5";
											CpitchN = 19 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 17 + startingKey) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;
									} else {

										if (rand1 >= 130) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 100) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										}
									}

									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 4M6 has just ended.

								else if (PitchNumberArray.get( (i-1) ) == 19 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter note, or
									// whole note.
									if (rand1 >= 180) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 140) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 60) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 4P5 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 17 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter note, or
									// whole note.
									if (PitchNumberArray.get(i - 2) == 24 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "4P5";
											CpitchN = 19 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "4P5";
											CpitchN = 19 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
										
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										
									} else if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
										
										CpitchS = "4M3";
										CpitchN = 16 + startingKey;
									
								} else {
										if (rand1 >= 170) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 150) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 100) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 95) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										}

									}

									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
								} // If the last note was 4P4 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 16 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter note, or
									// whole note.

									if (rand1 >= 190) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 175) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 125) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 70) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									}

									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
								} // If the last note was 4M3 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 14 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter note, or
									// whole note.

									if (PitchNumberArray.get(i - 2) == 24 + startingKey) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 16 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 17 + startingKey) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
										int randT = rand.nextInt(2);
										
										if (randT == 0) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
										

									} else if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
										int randT = rand.nextInt(2);
										if (randT == 0) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									
								}     else {

										if (rand1 >= 195) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 185) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 150) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 120) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 60) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										}
									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 4M2 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 12 + startingKey) {
									int rand1 = rand.nextInt(250);
									// rand3 is either eighth note, quarter note, or
									// whole note.
									if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
										int randT = rand.nextInt(2);
										if (randT == 0) {
											CpitchS = "3P5";
											CpitchN = 7 + startingKey;
										} else if (randT == 1) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										}
										

									} else {
									
									if (rand1 >= 235) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 220) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 190) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 140) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 60) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									}
									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 4R has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 11 + startingKey) {
									CpitchS = "4R";
									CpitchN = 12 + startingKey;
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
									
								}
								else if (PitchNumberArray.get( (i-1) ) == 9 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter note, or
									// whole note.
									if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "3P5";
											CpitchN = 7 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 2 + startingKey) {
										
											CpitchS = "3P5";
											CpitchN = 7 + startingKey;
										
									} else {
										if (rand1 >= 195) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 190) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 180) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 160) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 130) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 15) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3R";
											CpitchN = startingKey;

										}

									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3M6 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 7 + startingKey) {
									
									
									if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) > 0) {
										int rand1 = rand.nextInt(200);
									if (rand1 >= 190) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 185) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 150) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 130) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 110) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}
									} else if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) <= 0) {
										int rand1 = rand.nextInt(120);
										if (rand1 >= 110) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 40) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3R";
											CpitchN = startingKey;

										}
									
									
									}
									
									
									
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3P5 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 5 + startingKey) {
									int rand1 = rand.nextInt(200);
									
									if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
										int randT = rand.nextInt(10);

										if (randT >=9 ) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										} else if (randT >= 6) {
											CpitchS = "3P5";
											CpitchN = 7 + startingKey;
										} else if (randT >= 0) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == startingKey) {
										int randT = rand.nextInt(30);
										if (randT >= 25) {
											CpitchS = "3M6";
											CpitchN = 9 + startingKey;
										} else if (randT >= 15) {
											CpitchS = "3P5";
											CpitchN = 7 + startingKey;
										} else if (randT >= 0) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										}
									} else {

									if (rand1 >= 190) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 180) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 140) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 130) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}
									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3P4 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 4 + startingKey) {
									if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) >= 0) {
										int rand1 = rand.nextInt(200);
									if (rand1 >= 190) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 150) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 110) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 60) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}
									} else if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) <= 0) {
										int rand1 = rand.nextInt(150);
										if (rand1 >= 130) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 100) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 60) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 40) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3R";
											CpitchN = startingKey;

										}
									}
									
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3M3 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 2 + startingKey) {
									int rand1 = rand.nextInt(200);

									if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										} else if (randT == 1) {
											CpitchS = "3R";
											CpitchN = startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										} else if (randT == 1) {
											CpitchS = "3R";
											CpitchN = startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 12 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										} else if (randT == 1) {
											CpitchS = "3R";
											CpitchN = startingKey;
										}
									} else {
									
									if (rand1 >= 190) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 160) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 150) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 90) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}
									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3M2 has just ended.

								else if (PitchNumberArray.get( (i-1) ) == startingKey) {
									int rand1 = rand.nextInt(7);

									if (rand1 == 0) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 == 1) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 == 2) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 == 3) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 == 4) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 == 5) {

										CpitchS = "3R";
										CpitchN = startingKey;

									} else if (rand1 == 6) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3R has just ended.
								else {
									System.out.println("PROBLEM HERE 3!");
								}
							} else if (PhraseCountArray.get(i) >= 2) {
							
								if (bitcount <= 120) {
//		 							//I Major Pentatonic

									
									if (PitchNumberArray.get( (i-1) ) == 24 + startingKey) {

										int rand1 = rand.nextInt(200);

										if (rand1 >= 160) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 120) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} 

										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was the highest has just
										// ended.
									else if (PitchNumberArray.get( (i-1) ) == 23 + startingKey) {
										CpitchS = "5R";
										CpitchN = 24 + startingKey;
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);
										
									}

									else if (PitchNumberArray.get( (i-1) ) == 21 + startingKey) {
										int rand1 = rand.nextInt(200);
										// rand3 is either eighth note, quarter note, or
										// whole note.
										if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
											int randT = rand.nextInt(2);

											if (randT == 0) {
												CpitchS = "4P5";
												CpitchN = 19 + startingKey;
											} else if (randT == 1) {
												CpitchS = "4M3";
												CpitchN = 16 + startingKey;
											}
										} else if (PitchNumberArray.get(i - 2) == 17 + startingKey) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;
										} else {

											if (rand1 >= 130) {

												CpitchS = "5R";
												CpitchN = 24 + startingKey;

											} else if (rand1 >= 100) {

												CpitchS = "4M6";
												CpitchN = 21 + startingKey;

											} else if (rand1 >= 30) {

												CpitchS = "4P5";
												CpitchN = 19 + startingKey;

											} else if (rand1 >= 20) {

												CpitchS = "4M3";
												CpitchN = 16 + startingKey;

											} else if (rand1 >= 0) {

												CpitchS = "4R";
												CpitchN = 12 + startingKey;

											}
										}

										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 4M6 has just ended.

									else if (PitchNumberArray.get( (i-1) ) == 19 + startingKey) {
										int rand1 = rand.nextInt(200);
										// rand3 is either eighth note, quarter note, or
										// whole note.
										if (rand1 >= 180) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 140) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 120) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 60) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 40) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										}
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 4P5 has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 17 + startingKey) {
										int rand1 = rand.nextInt(200);
										// rand3 is either eighth note, quarter note, or
										// whole note.
										if (PitchNumberArray.get(i - 2) == 24 + startingKey) {
											int randT = rand.nextInt(2);

											if (randT == 0) {
												CpitchS = "4P5";
												CpitchN = 19 + startingKey;
											} else if (randT == 1) {
												CpitchS = "4M3";
												CpitchN = 16 + startingKey;
											}
										} else if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
											int randT = rand.nextInt(2);

											if (randT == 0) {
												CpitchS = "4P5";
												CpitchN = 19 + startingKey;
											} else if (randT == 1) {
												CpitchS = "4M3";
												CpitchN = 16 + startingKey;
											}
										} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
											
												CpitchS = "4M3";
												CpitchN = 16 + startingKey;
											
										} else if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
											
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										
									} else {
											if (rand1 >= 170) {

												CpitchS = "5R";
												CpitchN = 24 + startingKey;

											} else if (rand1 >= 150) {

												CpitchS = "4M6";
												CpitchN = 21 + startingKey;

											} else if (rand1 >= 100) {

												CpitchS = "4P5";
												CpitchN = 19 + startingKey;

											} else if (rand1 >= 95) {

												CpitchS = "4P4";
												CpitchN = 17 + startingKey;

											} else if (rand1 >= 50) {

												CpitchS = "4M3";
												CpitchN = 16 + startingKey;

											} else if (rand1 >= 30) {

												CpitchS = "4M2";
												CpitchN = 14 + startingKey;

											} else if (rand1 >= 20) {

												CpitchS = "4R";
												CpitchN = 12 + startingKey;

											} else if (rand1 >= 5) {

												CpitchS = "3M6";
												CpitchN = 9 + startingKey;

											} else if (rand1 >= 0) {

												CpitchS = "3P5";
												CpitchN = 7 + startingKey;

											}

										}

										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);
									} // If the last note was 4P4 has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 16 + startingKey) {
										int rand1 = rand.nextInt(200);
										// rand3 is either eighth note, quarter note, or
										// whole note.

										if (rand1 >= 190) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 175) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 125) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 100) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 70) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										}

										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);
									} // If the last note was 4M3 has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 14 + startingKey) {
										int rand1 = rand.nextInt(200);
										// rand3 is either eighth note, quarter note, or
										// whole note.

										if (PitchNumberArray.get(i - 2) == 24 + startingKey) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
											int randT = rand.nextInt(2);

											if (randT == 0) {
												CpitchS = "4R";
												CpitchN = 12 + startingKey;
											} else if (randT == 1) {
												CpitchS = "4M3";
												CpitchN = 16 + startingKey;
											}
										} else if (PitchNumberArray.get(i - 2) == 16 + startingKey) {
											int randT = rand.nextInt(2);

											if (randT == 0) {
												CpitchS = "4R";
												CpitchN = 12 + startingKey;
											} else if (randT == 1) {
												CpitchS = "4M3";
												CpitchN = 16 + startingKey;
											}
										} else if (PitchNumberArray.get(i - 2) == 17 + startingKey) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
											int randT = rand.nextInt(2);
											
											if (randT == 0) {
												CpitchS = "4R";
												CpitchN = 12 + startingKey;
											} else if (randT == 1) {
												CpitchS = "4M3";
												CpitchN = 16 + startingKey;
											}
											

										} else if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
											int randT = rand.nextInt(2);
											if (randT == 0) {
												CpitchS = "4R";
												CpitchN = 12 + startingKey;
											} else if (randT == 1) {
												CpitchS = "4M3";
												CpitchN = 16 + startingKey;
											}
										
									}     else {

											if (rand1 >= 195) {

												CpitchS = "5R";
												CpitchN = 24 + startingKey;

											} else if (rand1 >= 185) {

												CpitchS = "4M6";
												CpitchN = 21 + startingKey;

											} else if (rand1 >= 150) {

												CpitchS = "4P5";
												CpitchN = 19 + startingKey;

											} else if (rand1 >= 120) {

												CpitchS = "4P4";
												CpitchN = 17 + startingKey;

											} else if (rand1 >= 60) {

												CpitchS = "4M3";
												CpitchN = 16 + startingKey;

											} else if (rand1 >= 50) {

												CpitchS = "4M2";
												CpitchN = 14 + startingKey;

											} else if (rand1 >= 10) {

												CpitchS = "4R";
												CpitchN = 12 + startingKey;

											} else if (rand1 >= 5) {

												CpitchS = "3M6";
												CpitchN = 9 + startingKey;

											} else if (rand1 >= 0) {

												CpitchS = "3P5";
												CpitchN = 7 + startingKey;

											}
										}
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 4M2 has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 12 + startingKey) {
										int rand1 = rand.nextInt(250);
										// rand3 is either eighth note, quarter note, or
										// whole note.
										if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
											int randT = rand.nextInt(2);
											if (randT == 0) {
												CpitchS = "3P5";
												CpitchN = 7 + startingKey;
											} else if (randT == 1) {
												CpitchS = "3M3";
												CpitchN = 4 + startingKey;
											}
											

										} else {
										
										if (rand1 >= 235) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 220) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 190) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 170) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 140) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 100) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 60) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										}
										}
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 4R has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 11 + startingKey) {
										CpitchS = "4R";
										CpitchN = 12 + startingKey;
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);
									}
									else if (PitchNumberArray.get( (i-1) ) == 9 + startingKey) {
										int rand1 = rand.nextInt(200);
										// rand3 is either eighth note, quarter note, or
										// whole note.
										if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
											int randT = rand.nextInt(2);

											if (randT == 0) {
												CpitchS = "3P5";
												CpitchN = 7 + startingKey;
											} else if (randT == 1) {
												CpitchS = "4R";
												CpitchN = 12 + startingKey;
											}
										} else if (PitchNumberArray.get(i - 2) == 2 + startingKey) {
											
												CpitchS = "3P5";
												CpitchN = 7 + startingKey;
											
										} else {
											if (rand1 >= 195) {

												CpitchS = "4M6";
												CpitchN = 21 + startingKey;

											} else if (rand1 >= 190) {

												CpitchS = "4P5";
												CpitchN = 19 + startingKey;

											} else if (rand1 >= 180) {

												CpitchS = "4P4";
												CpitchN = 17 + startingKey;

											} else if (rand1 >= 160) {

												CpitchS = "4M3";
												CpitchN = 16 + startingKey;

											} else if (rand1 >= 130) {

												CpitchS = "4M2";
												CpitchN = 14 + startingKey;

											} else if (rand1 >= 80) {

												CpitchS = "4R";
												CpitchN = 12 + startingKey;

											} else if (rand1 >= 50) {

												CpitchS = "3M6";
												CpitchN = 9 + startingKey;

											} else if (rand1 >= 20) {

												CpitchS = "3P5";
												CpitchN = 7 + startingKey;

											} else if (rand1 >= 15) {

												CpitchS = "3P4";
												CpitchN = 5 + startingKey;

											} else if (rand1 >= 5) {

												CpitchS = "3M3";
												CpitchN = 4 + startingKey;

											} else if (rand1 >= 0) {

												CpitchS = "3R";
												CpitchN = startingKey;

											}

										}
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3M6 has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 7 + startingKey) {
										
										
										if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) > 0) {
											int rand1 = rand.nextInt(200);
										if (rand1 >= 190) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 185) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 150) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 130) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 110) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3R";
											CpitchN = startingKey;

										}
										} else if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) <= 0) {
											int rand1 = rand.nextInt(120);
											if (rand1 >= 110) {

												CpitchS = "4R";
												CpitchN = 12 + startingKey;

											} else if (rand1 >= 80) {

												CpitchS = "3M6";
												CpitchN = 9 + startingKey;

											} else if (rand1 >= 50) {

												CpitchS = "3P5";
												CpitchN = 7 + startingKey;

											} else if (rand1 >= 40) {

												CpitchS = "3P4";
												CpitchN = 5 + startingKey;

											} else if (rand1 >= 10) {

												CpitchS = "3M3";
												CpitchN = 4 + startingKey;

											} else if (rand1 >= 5) {

												CpitchS = "3M2";
												CpitchN = 2 + startingKey;

											} else if (rand1 >= 0) {

												CpitchS = "3R";
												CpitchN = startingKey;

											}
										
										
										}
										
										
										
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3P5 has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 5 + startingKey) {
										int rand1 = rand.nextInt(200);
										
										if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
											int randT = rand.nextInt(10);

											if (randT >=9 ) {
												CpitchS = "4R";
												CpitchN = 12 + startingKey;
											} else if (randT >= 6) {
												CpitchS = "3P5";
												CpitchN = 7 + startingKey;
											} else if (randT >= 0) {
												CpitchS = "3M3";
												CpitchN = 4 + startingKey;
											}
										} else if (PitchNumberArray.get(i - 2) == startingKey) {
											int randT = rand.nextInt(30);
											if (randT >= 25) {
												CpitchS = "3M6";
												CpitchN = 9 + startingKey;
											} else if (randT >= 15) {
												CpitchS = "3P5";
												CpitchN = 7 + startingKey;
											} else if (randT >= 0) {
												CpitchS = "3M3";
												CpitchN = 4 + startingKey;
											}
										} else {

										if (rand1 >= 190) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 180) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 140) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 130) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 40) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3R";
											CpitchN = startingKey;

										}
										}
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3P4 has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 4 + startingKey) {
										if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) > 0) {
											int rand1 = rand.nextInt(200);
										if (rand1 >= 190) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 170) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 150) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 110) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 60) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3R";
											CpitchN = startingKey;

										}
										} else if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) <= 0) {
											int rand1 = rand.nextInt(150);
											if (rand1 >= 130) {

												CpitchS = "4R";
												CpitchN = 12 + startingKey;

											} else if (rand1 >= 100) {

												CpitchS = "3M6";
												CpitchN = 9 + startingKey;

											} else if (rand1 >= 80) {

												CpitchS = "3P5";
												CpitchN = 7 + startingKey;

											} else if (rand1 >= 60) {

												CpitchS = "3P4";
												CpitchN = 5 + startingKey;

											} else if (rand1 >= 40) {

												CpitchS = "3M3";
												CpitchN = 4 + startingKey;

											} else if (rand1 >= 20) {

												CpitchS = "3M2";
												CpitchN = 2 + startingKey;

											} else if (rand1 >= 0) {

												CpitchS = "3R";
												CpitchN = startingKey;

											}
										}
										
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3M3 has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 2 + startingKey) {
										int rand1 = rand.nextInt(200);

										if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
											int randT = rand.nextInt(2);

											if (randT == 0) {
												CpitchS = "3M3";
												CpitchN = 4 + startingKey;
											} else if (randT == 1) {
												CpitchS = "3R";
												CpitchN = startingKey;
											}
										} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
											int randT = rand.nextInt(2);

											if (randT == 0) {
												CpitchS = "3M3";
												CpitchN = 4 + startingKey;
											} else if (randT == 1) {
												CpitchS = "3R";
												CpitchN = startingKey;
											}
										} else if (PitchNumberArray.get(i - 2) == 12 + startingKey) {
											int randT = rand.nextInt(2);

											if (randT == 0) {
												CpitchS = "3M3";
												CpitchN = 4 + startingKey;
											} else if (randT == 1) {
												CpitchS = "3R";
												CpitchN = startingKey;
											}
										} else {
										
										if (rand1 >= 190) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 160) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 150) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 100) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 90) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3R";
											CpitchN = startingKey;

										}
										}
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3M2 has just ended.

									else if (PitchNumberArray.get( (i-1) ) == startingKey) {
										int rand1 = rand.nextInt(7);

										if (rand1 == 0) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 == 1) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 == 2) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 == 3) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 == 4) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} else if (rand1 == 5) {

											CpitchS = "3R";
											CpitchN = startingKey;

										} else if (rand1 == 6) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										}
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3R has just ended.

								} else if (bitcount >= 120) {
									
									//V7 Harmony 

									if (PitchNumberArray.get( (i-1) ) == 24 + startingKey) {
										int rand1 = rand.nextInt(100);
										
										if (rand1 >= 20) {

											CpitchS = "4M7";
											CpitchN = 23 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} 
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 5R has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 23 + startingKey) {
										int rand1 = rand.nextInt(200);
										
										if (rand1 >= 170) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 160) {

											CpitchS = "4M7";
											CpitchN = 23 + startingKey;

										} else if (rand1 >= 130) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 40) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} 
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);
										
										
									} // The note 4M7 has just ended
									else if (PitchNumberArray.get( (i-1) ) == 21 + startingKey) {
										int rand1 = rand.nextInt(200);
										
										if (rand1 >= 180 ) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 120) {

											CpitchS = "4M7";
											CpitchN = 23 + startingKey;

										} else if (rand1 >= 110) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										}
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 4M6 has just
										// ended.

									else if (PitchNumberArray.get( (i-1) ) == 19 + startingKey) {
										int rand1 = rand.nextInt(200);
										
										if (rand1 >= 180) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 155) {

											CpitchS = "4M7";
											CpitchN = 23 + startingKey;

										} else if (rand1 >= 135) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 110) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "3M7";
											CpitchN = 11 + startingKey;

										} 
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 4P5 has just
										// ended.
									else if (PitchNumberArray.get( (i-1) ) == 17 + startingKey) {
										int rand1 = rand.nextInt(200);
										
										if (rand1 >= 195) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 185) {

											CpitchS = "4M7";
											CpitchN = 23 + startingKey;

										} else if (rand1 >= 140) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 100) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 70) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 15) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "3M7";
											CpitchN = 11 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										}

										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);
									} // If the last note was 4P4 has just
										// ended.
									else if (PitchNumberArray.get( (i-1) ) == 16 + startingKey) {
										int rand1 = rand.nextInt(200);
										// rand3 is either eighth note, quarter
										// note, or
										// whole note.
										 if (rand1 >= 195) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 145) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 85) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 24) {

											CpitchS = "3M7";
											CpitchN = 11 + startingKey;

										} else if (rand1 >= 18) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										}

										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);
									} // If the last note was 4M3 has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 14 + startingKey) {
										int rand1 = rand.nextInt(200);
										// rand3 is either eighth note, quarter
										// note, or
										// whole note.
										if (rand1 >= 195) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 190) {

											CpitchS = "4M7";
											CpitchN = 23 + startingKey;

										} else if (rand1 >= 170) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 145) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 120) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 90) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 65) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 40) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "3M7";
											CpitchN = 11 + startingKey;

										} else if (rand1 >= 15) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										}
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 4M2 has just
										// ended.
									else if (PitchNumberArray.get( (i-1) ) == 12 + startingKey) {
										int rand1 = rand.nextInt(100);
									
									if (rand1 >= 50) {

										CpitchS = "3M7";
										CpitchN = 11 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "4M2";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} 
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
									} // If the last note was 4R has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 11 + startingKey) {
										int rand1 = rand.nextInt(200);
										
										if (rand1 >= 180) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 170) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 140) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 110) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "3M7";
											CpitchN = 11 + startingKey;

										} else if (rand1 >= 40) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										}  
										
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3M7 has just ended.
									else if (PitchNumberArray.get( (i-1) ) == 9 + startingKey) {
										int rand1 = rand.nextInt(200);
										
										 if (rand1 >= 195) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 170) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 140) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 120) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "3M7";
											CpitchN = 11 + startingKey;

										} else if (rand1 >= 60) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} 
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3M6 has just
										// ended.
									else if (PitchNumberArray.get( (i-1) ) == 7 + startingKey) {
										int rand1 = rand.nextInt(200);

										if (rand1 >= 195) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 190) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 175) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 165) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 120) {

											CpitchS = "3M7";
											CpitchN = 11 + startingKey;

										} else if (rand1 >= 90) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 60) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} 
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3P5 has just
										// ended.
									else if (PitchNumberArray.get( (i-1) ) == 5 + startingKey) {
										int rand1 = rand.nextInt(200);

										 if (rand1 >= 190) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 185) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 175) {

											CpitchS = "3M7";
											CpitchN = 11 + startingKey;

										} else if (rand1 >= 120) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 70) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 55) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} 
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3P4 has just
										// ended.
									else if (PitchNumberArray.get( (i-1) ) == 4 + startingKey) {
										int rand1 = rand.nextInt(10);

										if (rand1 >= 8) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 4) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										}  else if (rand1 >= 0) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} 
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3M3 has just
										// ended.
									else if (PitchNumberArray.get( (i-1) ) == 2 + startingKey) {
										int rand1 = rand.nextInt(200);

										if (rand1 >= 190) {

											CpitchS = "3M7";
											CpitchN = 11 + startingKey;

										} else if (rand1 >= 170) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 125) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 70) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 40) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} 
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3M2 has just
										// ended.

									else if (PitchNumberArray.get( (i-1) ) == startingKey) {
										int rand1 = rand.nextInt(200);

										if (rand1 >= 195) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 120) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 90) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} 
										PitchArray.add(CpitchS);
										PitchNumberArray.add(CpitchN);

									} // If the last note was 3R has just ended.

								} else {
									System.out.println("PROBLEM HERE 5!");
								}
							}
						}// ///////////////Measure 3//
								
							else if (ExtraMeasureCountArray.get(i) == 4) {
								
								
								if (PhraseCountArray.get(i) == 1) { 

								if (PitchNumberArray.get( (i-1) ) == 24 + startingKey) {
									
									int rand1 = rand.nextInt(100);
									
									if (rand1 >= 20) {

										CpitchS = "4M7";
										CpitchN = 23 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} 
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 5R has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 23 + startingKey) {
									int rand1 = rand.nextInt(200);
									
									if (rand1 >= 170) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 160) {

										CpitchS = "4M7";
										CpitchN = 23 + startingKey;

									} else if (rand1 >= 130) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} 
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
									
									
								} // The note 4M7 has just ended
								else if (PitchNumberArray.get( (i-1) ) == 21 + startingKey) {
									int rand1 = rand.nextInt(200);
									
									if (rand1 >= 180 ) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "4M7";
										CpitchN = 23 + startingKey;

									} else if (rand1 >= 110) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 4M6 has just
									// ended.

								else if (PitchNumberArray.get( (i-1) ) == 19 + startingKey) {
									int rand1 = rand.nextInt(200);
									
									if (rand1 >= 180) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 155) {

										CpitchS = "4M7";
										CpitchN = 23 + startingKey;

									} else if (rand1 >= 135) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 110) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3M7";
										CpitchN = 11 + startingKey;

									} 
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 4P5 has just
									// ended.
								else if (PitchNumberArray.get( (i-1) ) == 17 + startingKey) {
									int rand1 = rand.nextInt(200);
									
									if (rand1 >= 195) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 185) {

										CpitchS = "4M7";
										CpitchN = 23 + startingKey;

									} else if (rand1 >= 140) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 70) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 15) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "3M7";
										CpitchN = 11 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									}

									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
								} // If the last note was 4P4 has just
									// ended.
								else if (PitchNumberArray.get( (i-1) ) == 16 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter
									// note, or
									// whole note.
									 if (rand1 >= 195) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 145) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 85) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 24) {

										CpitchS = "3M7";
										CpitchN = 11 + startingKey;

									} else if (rand1 >= 18) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									}

									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
								} // If the last note was 4M3 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 14 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter
									// note, or
									// whole note.
									if (rand1 >= 195) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 190) {

										CpitchS = "4M7";
										CpitchN = 23 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 145) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 90) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 65) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "3M7";
										CpitchN = 11 + startingKey;

									} else if (rand1 >= 15) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 4M2 has just
									// ended.
								else if (PitchNumberArray.get( (i-1) ) == 12 + startingKey) {
									int rand1 = rand.nextInt(100);
								
								if (rand1 >= 50) {

									CpitchS = "3M7";
									CpitchN = 11 + startingKey;

								} else if (rand1 >= 20) {

									CpitchS = "4M2";
									CpitchN = 19 + startingKey;

								} else if (rand1 >= 5) {

									CpitchS = "3P5";
									CpitchN = 7 + startingKey;

								} else if (rand1 >= 0) {

									CpitchS = "4P4";
									CpitchN = 17 + startingKey;

								} 
								PitchArray.add(CpitchS);
								PitchNumberArray.add(CpitchN);
								} // If the last note was 4R has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 11 + startingKey) {
									int rand1 = rand.nextInt(200);
									
									if (rand1 >= 180) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 140) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 110) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "3M7";
										CpitchN = 11 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									}  
									
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3M7 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 9 + startingKey) {
									int rand1 = rand.nextInt(200);
									
									 if (rand1 >= 195) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 140) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "3M7";
										CpitchN = 11 + startingKey;

									} else if (rand1 >= 60) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} 
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3M6 has just
									// ended.
								else if (PitchNumberArray.get( (i-1) ) == 7 + startingKey) {
									int rand1 = rand.nextInt(200);

									if (rand1 >= 195) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 190) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 175) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 165) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "3M7";
										CpitchN = 11 + startingKey;

									} else if (rand1 >= 90) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 60) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} 
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3P5 has just
									// ended.
								else if (PitchNumberArray.get( (i-1) ) == 5 + startingKey) {
									int rand1 = rand.nextInt(200);

									 if (rand1 >= 190) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 185) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 175) {

										CpitchS = "3M7";
										CpitchN = 11 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 70) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 55) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} 
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3P4 has just
									// ended.
								else if (PitchNumberArray.get( (i-1) ) == 4 + startingKey) {
									int rand1 = rand.nextInt(10);

									if (rand1 >= 8) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 4) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									}  else if (rand1 >= 0) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} 
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3M3 has just
									// ended.
								else if (PitchNumberArray.get( (i-1) ) == 2 + startingKey) {
									int rand1 = rand.nextInt(200);

									if (rand1 >= 190) {

										CpitchS = "3M7";
										CpitchN = 11 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 125) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 70) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} 
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3M2 has just
									// ended.

								else if (PitchNumberArray.get( (i-1) ) == startingKey) {
									int rand1 = rand.nextInt(200);

									if (rand1 >= 195) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 90) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} 
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3R has just ended.
								else {
									System.out.println("PROBLEM HERE 4!");
								}
							}// Phrase 1 Measure 4
					else if (PhraseCountArray.get(i) >= 2) {
								//I Major pentatonic

								
								if (PitchNumberArray.get( (i-1) ) == 24 + startingKey) {

									int rand1 = rand.nextInt(200);

									if (rand1 >= 160) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} 

									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} else if (PitchNumberArray.get( (i-1) ) == 23 + startingKey) {
									CpitchS = "5R";
									CpitchN = 24 + startingKey;
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
								} else if (PitchNumberArray.get( (i-1) ) == 21 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter note, or
									// whole note.
									if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "4P5";
											CpitchN = 19 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 17 + startingKey) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;
									} else {

										if (rand1 >= 130) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 100) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										}
									}

									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} else if (PitchNumberArray.get( (i-1) ) == 19 + startingKey) {
									int rand1 = rand.nextInt(200);
								
									if (rand1 >= 180) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 140) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 120) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 60) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} else if (PitchNumberArray.get( (i-1) ) == 17 + startingKey) {
									int rand1 = rand.nextInt(200);
									
									if (PitchNumberArray.get(i - 2) == 24 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "4P5";
											CpitchN = 19 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "4P5";
											CpitchN = 19 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
										
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										
									} else if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
										
										CpitchS = "4M3";
										CpitchN = 16 + startingKey;
									
								} else {
										if (rand1 >= 170) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 150) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 100) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 95) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 30) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										}

									}

									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
								} // If the last note was 4P4 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 16 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter note, or
									// whole note.

									if (rand1 >= 190) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 175) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 125) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 70) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 20) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									}

									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
								} // If the last note was 4M3 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 14 + startingKey) {
									int rand1 = rand.nextInt(200);
									

									if (PitchNumberArray.get(i - 2) == 24 + startingKey) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 16 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 17 + startingKey) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
										int randT = rand.nextInt(2);
										
										if (randT == 0) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
										

									} else if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
										int randT = rand.nextInt(2);
										if (randT == 0) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4M3";
											CpitchN = 16 + startingKey;
										}
									
								}     else {

										if (rand1 >= 195) {

											CpitchS = "5R";
											CpitchN = 24 + startingKey;

										} else if (rand1 >= 185) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 150) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 120) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 60) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										}
									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 4M2 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 12 + startingKey) {
									int rand1 = rand.nextInt(250);
									// rand3 is either eighth note, quarter note, or
									// whole note.
									if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
										int randT = rand.nextInt(2);
										if (randT == 0) {
											CpitchS = "3P5";
											CpitchN = 7 + startingKey;
										} else if (randT == 1) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										}
	
									} else {
									
									if (rand1 >= 235) {

										CpitchS = "5R";
										CpitchN = 24 + startingKey;

									} else if (rand1 >= 220) {

										CpitchS = "4M6";
										CpitchN = 21 + startingKey;

									} else if (rand1 >= 190) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 140) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 60) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									}
									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 4R has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 11 + startingKey) {
									CpitchS = "4R";
									CpitchN = 12 + startingKey;
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);
									
								}
								else if (PitchNumberArray.get( (i-1) ) == 9 + startingKey) {
									int rand1 = rand.nextInt(200);
									// rand3 is either eighth note, quarter note, or
									// whole note.
									if (PitchNumberArray.get(i - 2) == 14 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "3P5";
											CpitchN = 7 + startingKey;
										} else if (randT == 1) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 2 + startingKey) {
										
											CpitchS = "3P5";
											CpitchN = 7 + startingKey;
										
									} else {
										if (rand1 >= 195) {

											CpitchS = "4M6";
											CpitchN = 21 + startingKey;

										} else if (rand1 >= 190) {

											CpitchS = "4P5";
											CpitchN = 19 + startingKey;

										} else if (rand1 >= 180) {

											CpitchS = "4P4";
											CpitchN = 17 + startingKey;

										} else if (rand1 >= 160) {

											CpitchS = "4M3";
											CpitchN = 16 + startingKey;

										} else if (rand1 >= 130) {

											CpitchS = "4M2";
											CpitchN = 14 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 15) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3R";
											CpitchN = startingKey;

										}

									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} else if (PitchNumberArray.get( (i-1) ) == 7 + startingKey) {
									
									
									if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) > 0) {
										
										int rand1 = rand.nextInt(200);
									if (rand1 >= 190) {

										CpitchS = "4P5";
										CpitchN = 19 + startingKey;

									} else if (rand1 >= 185) {

										CpitchS = "4P4";
										CpitchN = 17 + startingKey;

									} else if (rand1 >= 150) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 130) {

										CpitchS = "4M2";
										CpitchN = 14 + startingKey;

									} else if (rand1 >= 110) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 50) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 5) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}
									} else if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) <= 0) {
										
										int rand1 = rand.nextInt(120);
										if (rand1 >= 110) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 50) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 40) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 10) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 5) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3R";
											CpitchN = startingKey;

										}
									
									
									}
									
									
									
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} else if (PitchNumberArray.get( (i-1) ) == 5 + startingKey) {
									int rand1 = rand.nextInt(200);
									
									if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
										int randT = rand.nextInt(10);

										if (randT >=9 ) {
											CpitchS = "4R";
											CpitchN = 12 + startingKey;
										} else if (randT >= 6) {
											CpitchS = "3P5";
											CpitchN = 7 + startingKey;
										} else if (randT >= 0) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == startingKey) {
										int randT = rand.nextInt(30);
										if (randT >= 25) {
											CpitchS = "3M6";
											CpitchN = 9 + startingKey;
										} else if (randT >= 15) {
											CpitchS = "3P5";
											CpitchN = 7 + startingKey;
										} else if (randT >= 0) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										}
									} else {

									if (rand1 >= 190) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 180) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 140) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 130) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 40) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 10) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}
									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3P4 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 4 + startingKey) {
									if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) > 0) {
										int rand1 = rand.nextInt(200);
									if (rand1 >= 190) {

										CpitchS = "4M3";
										CpitchN = 16 + startingKey;

									} else if (rand1 >= 170) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									} else if (rand1 >= 150) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 110) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 80) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 60) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 30) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}
									} else if ( PitchNumberArray.get(i - 2) - PitchNumberArray.get( (i-1) ) <= 0) {
										int rand1 = rand.nextInt(150);
										if (rand1 >= 130) {

											CpitchS = "4R";
											CpitchN = 12 + startingKey;

										} else if (rand1 >= 100) {

											CpitchS = "3M6";
											CpitchN = 9 + startingKey;

										} else if (rand1 >= 80) {

											CpitchS = "3P5";
											CpitchN = 7 + startingKey;

										} else if (rand1 >= 60) {

											CpitchS = "3P4";
											CpitchN = 5 + startingKey;

										} else if (rand1 >= 40) {

											CpitchS = "3M3";
											CpitchN = 4 + startingKey;

										} else if (rand1 >= 20) {

											CpitchS = "3M2";
											CpitchN = 2 + startingKey;

										} else if (rand1 >= 0) {

											CpitchS = "3R";
											CpitchN = startingKey;

										}
									}
									
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3M3 has just ended.
								else if (PitchNumberArray.get( (i-1) ) == 2 + startingKey) {
									int rand1 = rand.nextInt(200);

									if (PitchNumberArray.get(i - 2) == 7 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										} else if (randT == 1) {
											CpitchS = "3R";
											CpitchN = startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 9 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										} else if (randT == 1) {
											CpitchS = "3R";
											CpitchN = startingKey;
										}
									} else if (PitchNumberArray.get(i - 2) == 12 + startingKey) {
										int randT = rand.nextInt(2);

										if (randT == 0) {
											CpitchS = "3M3";
											CpitchN = 4 + startingKey;
										} else if (randT == 1) {
											CpitchS = "3R";
											CpitchN = startingKey;
										}
									} else {
									
									if (rand1 >= 190) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 >= 160) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 >= 150) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 >= 100) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 >= 90) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 >= 0) {

										CpitchS = "3R";
										CpitchN = startingKey;

									}
									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3M2 has just ended.

								else if (PitchNumberArray.get( (i-1) ) == startingKey) {
									int rand1 = rand.nextInt(7);

									if (rand1 == 0) {

										CpitchS = "3M6";
										CpitchN = 9 + startingKey;

									} else if (rand1 == 1) {

										CpitchS = "3P5";
										CpitchN = 7 + startingKey;

									} else if (rand1 == 2) {

										CpitchS = "3P4";
										CpitchN = 5 + startingKey;

									} else if (rand1 == 3) {

										CpitchS = "3M3";
										CpitchN = 4 + startingKey;

									} else if (rand1 == 4) {

										CpitchS = "3M2";
										CpitchN = 2 + startingKey;

									} else if (rand1 == 5) {

										CpitchS = "3R";
										CpitchN = startingKey;

									} else if (rand1 == 6) {

										CpitchS = "4R";
										CpitchN = 12 + startingKey;

									}
									PitchArray.add(CpitchS);
									PitchNumberArray.add(CpitchN);

								} // If the last note was 3R has just ended.
								else {
									System.out.println("PROBLEM HERE 6!");
								}

							}//Phrase 2/Measure444444444444444444444444444

						}//Measure 4 ///////////4444444444444444444444444444444444444
						 

					}// /Phrase 1 & 2 just ended
						// //////////////////////////////////////////////////////////////////////1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1P1

					// PitchArray and PitchNumberArray should have been decided
					// so far.

					// VELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENT

					if (bitcount == 30) {

						int rand4 = rand.nextInt(9);

						int Cvelocity = 40 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 60) {

						int rand4 = rand.nextInt(9);

						int Cvelocity = 65 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 90) {

						int rand4 = rand.nextInt(9);

						int Cvelocity = 40 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 150) {

						int rand4 = rand.nextInt(9);

						int Cvelocity = 40 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 180) {
						int rand4 = rand.nextInt(9);

						int Cvelocity = 65 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 210) {

						int rand4 = rand.nextInt(9);

						int Cvelocity = 40 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 0) {
						int rand4 = rand.nextInt(9);
						int Cvelocity = 55 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 120) {
						int rand4 = rand.nextInt(9);
						int Cvelocity = 55 + rand4;
						VelocityArray.add(Cvelocity);

					} else if (bitcount == 240) {
						int rand4 = rand.nextInt(9);
						int Cvelocity = 55 + rand4;
						VelocityArray.add(Cvelocity); // At this point, the
														// (ith)
						// element
						// should be filled.

					}
					// VELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENTVELOCITYSTATEMENT

//RHYTHM STATEMENT START RHYTHMSTATEMENTSTART
					
					//double rand3 = rand.nextInt(4); // (0, 1, 2, or 3)//RHYTHMIC VALUE
					

					double CRt = 0.0;
					if(bitcount == 0) {
						if (ExtraMeasureCountArray.get(i) == 4) {
							if (PhraseCountArray.get(i) == 1) {
								double rand3 = rand.nextInt(30);
								if (rand3 >= 20) {
									CRt = 1.0;
								} else if (rand3 >=5 ) {
									CRt = .5;
								} else if (rand3 >=1 ) {
									CRt = .25;
								} else if (rand3 == 0 ) {
									CRt = .125;
								}
							} else if(PhraseCountArray.get(i) == 2){ 
								double rand3 = rand.nextInt(30);
								if (rand3 >= 17) {
									CRt = 1.0;
								} else if (rand3 >=5 ) {
									CRt = .5;
								} else if (rand3 >=1 ) {
									CRt = .25;
								} else if (rand3 == 0 ) {
									CRt = .125;
								}
								
							}
						} else {
						double rand3 = rand.nextInt(30);
						if (rand3 >= 28) {
							CRt = 1.0;
						} else if (rand3 >=23 ) {
							CRt = .5;
						} else if (rand3 >=10 ) {
							CRt = .25;
						} else if (rand3 >= 0 ) {
							CRt = .125;
						}
						}
					} else if (bitcount == 30) {
						
						CRt = (.125);
						
					} else if (bitcount == 60) {
						double rand3 = rand.nextInt(30);
						 if (rand3 >=28 ) {
							CRt = .5;
						} else if (rand3 >=10 ) {
							CRt = .25;
						} else if (rand3 >= 0 ) {
							CRt = .125;
						}
						
					} else if (bitcount == 90) {
						 CRt = (.125);
						
					} else if (bitcount == 120) {
						double rand3 = rand.nextInt(30);
						 if (rand3 >=28 ) {
							CRt = .5;
						} else if (rand3 >=10 ) {
							CRt = .25;
						} else if (rand3 >= 0 ) {
							CRt = .125;
						}
						
					} else if (bitcount == 150) {
						 CRt = (.125);
						
					} else if (bitcount == 180) {
						double rand3 = rand.nextInt(2);
						
						if (rand3 == 0) {
							
						CRt = (.25);
						} else if (rand3 == 1) {
							
						CRt = (.125);
								
						}
						
					} else if (bitcount == 210) {
					 CRt = (.125);
						
					} else {
						System.out.println("Problem Here Rhythm");	
						 CRt = (.125);
					}
					
					double CR = CRt;
					
					RhythmArray.add(CR);
					
					if (CR == .125) {
						numberof8thnotes++;
					}

					
					
					// RhythmEnd 
					// System.out.println(Cpitch + " " + CR + " " + Cvelocity);

					i++;

					// The entire rhythmic placing system is based on layers:
					if (CR == 1.0) {
						bitcount += 240;

					} else if (CR == .5) {
						bitcount += 120;

					} else if (CR == .25) {
						bitcount += 60;

					} else if (CR == .125) {
						bitcount += 30;

					}
					BitCountArray.add(bitcount); // ith element is filled

					if (bitcount == 240) {
						bitcount = bitcount - 240;

					} else if (bitcount > 240 ) {
						
						System.out.println("PROBLEM HERE");
					}

					// Now that bitcount is set, we can set Beat:
					 if (BitCountArray.get(i) == 240) {

						intermeasurecount = 1;
					
					 } else if (BitCountArray.get(i) >= 180) {

						intermeasurecount = 4;
					} else if (BitCountArray.get(i) >= 120) {

						intermeasurecount = 3;
					} else if (BitCountArray.get(i)>= 60) {

						intermeasurecount = 2;
					} else if (BitCountArray.get(i)>= 0) {

						intermeasurecount = 1;

					}
					InterMeasureCountArray.add(intermeasurecount); // ith
																	// element
																	// is
																	// filled

					// FOR NOW THIS ONLY WORKS BECAUSE THERE's NO POSSIBILITY
					// FOR
					// THE
					// NEW ADDITION TO EXCEED A HALF NOTE.
					if (BitCountArray.get(i) - BitCountArray.get(i-1) == 0) {

						extrameasurecount++;
					} 
					if (InterMeasureCountArray.get( (i-1) ) > InterMeasureCountArray
							.get(i)) {
						extrameasurecount++;
					}
					 if (extrameasurecount > 4) {
						extrameasurecount = extrameasurecount - 4;
					}
					
					

					ExtraMeasureCountArray.add(extrameasurecount);

					if (ExtraMeasureCountArray.get( (i-1) ) > ExtraMeasureCountArray
							.get(i)) {
						phrasecount++;
					}
					

					PhraseCountArray.add(phrasecount);// 0th element is filled,
														// 1st element is
														// partially filled.

					// ////////////////////////////////////////////////////////////////
				} // i2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2ENDi2END

		/*	BUGS	
	System.out.print(i);System.out.println(BitCountArray);
	System.out.println(InterMeasureCountArray);
	System.out.println(PitchArray);
	System.out.println(PitchNumberArray);
	System.out.println(PhraseCountArray);*/
	
			}// WHILEWHILEWHILEWHILEWHILEWHILEWHILEWHILEWHILEWHILEENDSENDSENDSENDEDNENEDNDEDENDED

		

		// Now convert the values in the arrays into values that can
		// be read by MIDI

		// notenum exists to figure out the length of the track. It is the
		// number of notes.
		int notenum = i;
		String[] voice1pitches = new String[notenum];
		String[] voice1velocities = new String[notenum];
		String[] voice1lengths = new String[notenum];

		for (int t = 0; t < notenum; t++) {

			// Here we need a converter of notes to Hex. This is for the
			// pitches part.
			if (PitchArray.get(t).equals("3R")) {
				voice1pitches[t] = "37";
			} else if (PitchArray.get(t).equals("3M2")) {
				voice1pitches[t] = "39";
			} else if (PitchArray.get(t).equals("3M3")) {
				voice1pitches[t] = "3B";
			} else if (PitchArray.get(t).equals("3P4")) {
				voice1pitches[t] = "3C";
			} else if (PitchArray.get(t).equals("3P5")) {
				voice1pitches[t] = "3E";
			} else if (PitchArray.get(t).equals("3M6")) {
				voice1pitches[t] = "40";
			} else if (PitchArray.get(t).equals("3m7")) {
				voice1pitches[t] = "41";
			} else if (PitchArray.get(t).equals("3M7")) {
				voice1pitches[t] = "42";
			} else if (PitchArray.get(t).equals("4R")) {
				voice1pitches[t] = "43";
			} else if (PitchArray.get(t).equals("4M2")) {
				voice1pitches[t] = "45";
			} else if (PitchArray.get(t).equals("4M3")) {
				voice1pitches[t] = "47";
			} else if (PitchArray.get(t).equals("4P4")) {
				voice1pitches[t] = "48";
			} else if (PitchArray.get(t).equals("4P5")) {
				voice1pitches[t] = "4A";
			} else if (PitchArray.get(t).equals("4M6")) {
				voice1pitches[t] = "4C";
			} else if (PitchArray.get(t).equals("4m7")) {
				voice1pitches[t] = "4D";
			} else if (PitchArray.get(t).equals("4M7")) {
				voice1pitches[t] = "4E";
			} else if (PitchArray.get(t).equals("5R")) {
				voice1pitches[t] = "4F";
			}
			// This is for the velocities part:
			voice1velocities[t] = Integer.toHexString(VelocityArray.get(t));

			// This is for the rhythms:

			if (RhythmArray.get(t) == .125) {
				voice1lengths[t] = "40";

			} else if (RhythmArray.get(t) == .25) {
				voice1lengths[t] = "81 00";

			} else if (RhythmArray.get(t) == .5) {
				voice1lengths[t] = "82 00";

			} else if (RhythmArray.get(t) == 1.0) {
				voice1lengths[t] = "84 00";

			}
		}

		System.out.println(BitCountArray);
		System.out.println(RhythmArray);
		System.out.println(InterMeasureCountArray);
		System.out.println(ExtraMeasureCountArray);
		System.out.println(PhraseCountArray);
		

		System.out.print(Arrays.toString(voice1pitches));
		System.out.println();
		System.out.print(Arrays.toString(voice1velocities));
		System.out.println();
		System.out.print(Arrays.toString(voice1lengths));
		System.out.println();
		System.out.println(numberof8thnotes);

		File file = new File(
				"/Users/EXAMPLEUSER/Desktop/MIDIFiles/Samples/exampleMIDI.txt");

		String writtenfilelengtht;
		int filelength = 18 + (9 * notenum) - numberof8thnotes;
		if (filelength < 256) {

			writtenfilelengtht = "00 " + Integer.toHexString(filelength) + " ";
		} else {
			String hex = Integer.toHexString(filelength);
			String[] parts = hex.split("");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];

			writtenfilelengtht = "0" + part1 + " " + part2 + part3 + " ";
		}

		String KK = "01";
		String writtenfilelength = writtenfilelengtht;

		try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
			NoteArrays now = new NoteArrays();

			// This part writes the header and unchangeable stuff
			br.write("4D 54 68 64 00 00 00 06 00 00 00 01 00 80 4D 54 72 6B 00 00 ");
			// Now we write the size of the file: the number of bytes after this
			// marker.
			// This part is based on an int, which needs to be converted to hex
			// The easiest way is to use Integer.toHexString(int). filelength
			// will be the name of that int.
			br.write(writtenfilelength);
			// This next part prepares the time signature
			br.write("00 FF 58 04 ");
			// This next part writes the time signature. Eventually add
			// variables to this.
			br.write("04 02 ");
			// This is the MIDI CLOCK PART, not sure how to make this
			// variable yet.
			br.write("30 08 ");
			// This is the key signature part. KK stands for number of sharps or
			// flats.
			br.write("00 FF 59 02 " + KK + " 00 00 ");
			// Now begin the notes. Assuming there will be one
			// note at a time.
			// Need an array for the pitches, the velocities, and the note
			// lengths.
			// Three arrays that are combined.
			for (int j = 0; j < notenum; j++) {
				br.write("90 " + voice1pitches[j] + " " + voice1velocities[j]
						+ " " + voice1lengths[j] + " 90 " + voice1pitches[j]
						+ " 00 00 ");

			}
			br.write("FF 2F 00");

		} catch (IOException e) {

			System.out.println("Unable to read file " + file.toString());
		}
		System.out.println(filelength);
		System.out.println(Integer.toHexString(filelength) + " ");

	}

}
