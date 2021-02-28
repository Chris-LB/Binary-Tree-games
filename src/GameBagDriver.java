
public class GameBagDriver {

	public static void main(String[] args) {
		Game horizon = new Game("Horizon zero dawn", 45);
		Game godOfwar = new Game("God of War", 78);
		Game redDead = new Game("Red Dead Redemption",100);
		Game batman = new Game ("Batman Arkham Knight", 56);
		Game uncharted = new Game("Uncharted a Thief's end", 50);
		
		GameBSTLinkedBag firstTry = new GameBSTLinkedBag();
		GameBSTLinkedBag secondTry = new GameBSTLinkedBag();
		firstTry.add(horizon);
		firstTry.add(godOfwar);
		firstTry.add(redDead);
		secondTry.add(batman);
		secondTry.add(uncharted);
		
		firstTry.display();
		firstTry.displayLowToHigh();
		System.out.println(" \n"+secondTry.contains("Batman Arkham Knight"));
		secondTry.search("Batman Arkham Knight");
		System.out.println(firstTry.countOfOccurance(redDead));
		System.out.println(firstTry.total());
		firstTry.remove(redDead);
		System.out.println("  ");
		firstTry.addAll(secondTry);
		firstTry.display();
	}

}
