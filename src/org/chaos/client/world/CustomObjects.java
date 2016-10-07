package org.chaos.client.world;

import org.chaos.client.Client;

/**
 * Custom object spawns
 * 
 * @author Gabbe
 */
public class CustomObjects {

	/**
	 * Spawns the objects to the map
	 */
	public static void spawn() {
		for (int i = 0; i < CUSTOM_OBJECTS.length; i++) {
			int id = CUSTOM_OBJECTS[i][0];
			int x = CUSTOM_OBJECTS[i][1];
			int y = CUSTOM_OBJECTS[i][2];
			int z = CUSTOM_OBJECTS[i][3];
			int face = CUSTOM_OBJECTS[i][4];
			Client.instance.addObject(x, y, id, face, 10, z);
		}
	}

	public static final int[][] CUSTOM_OBJECTS = {
			//Rfd Chest & Portal
			{ 2182, 3081, 3495, 0, 3 },
			{ 12356, 3077, 3492, 0, 2 },
			//rune ore @ BH
			{ 14859, 3118, 3690, 0, 1 },
			{ 14859, 3117, 3691, 0, 1 },
			{ 14859, 3119, 3689, 0, 1 },
			{ 14859, 3117, 3688, 0, 1 },
			{ 14859, 3116, 3688, 0, 1 },
			{ 14859, 3115, 3690, 0, 1 },
			{ 14859, 3116, 3689, 0, 1 },
			//giant crystal
			/* Training Grounds Skillzone */
			//giant crystal
			{ 62, 2508, 3363, 0, 2 },
			//dummies
			{ -1, 2516, 3369, 0, 0 },
			{ -1, 2514, 3369, 0, 0 },
			{ -1, 2514, 3367, 0, 0 },
			{ -1, 2515, 3365, 0, 0 },
			{ -1, 2516, 3370, 0, 0 },
			{ -1, 2513, 3371, 0, 0 },
			{ -1, 2511, 3365, 0, 0 },
			{ -1, 2511, 3369, 0, 0 },
			{ -1, 2511, 3373, 0, 0 },
			{ -1, 2510, 3367, 0, 0 },
			{ -1, 2508, 3366, 0, 0 },
			{ -1, 2509, 3371, 0, 0 },
			{ -1, 2507, 3368, 0, 0 },
			{ -1, 2505, 3370, 0, 0 },
			{ -1, 2514, 3384, 0, 0 }, // table
			//bank booths
			{ 2213, 2515, 3383, 0, 2 },
			{ 2213, 2514, 3383, 0, 2 },
			//ores
			{ 9709, 2523, 3377, 0, 0 },
			{ 9708, 2524, 3377, 0, 0 },
			{ 9715, 2525, 3377, 0, 0 },
			{ 9714, 2526, 3377, 0, 0 },
			{ 9718, 2527, 3377, 0, 0 },
			{ 9717, 2528, 3377, 0, 0 },
			{ 29217, 2529, 3377, 0, 0 },
			{ 29215, 2530, 3377, 0, 0 },
			{ 9720, 2531, 3377, 0, 0 },
			{ 25370, 2532, 3377, 0, 0 },
			{ 11941, 2533, 3377, 0, 0 },
			{ 9708, 2523, 3373, 0, 0 },
			{ 9714, 2524, 3373, 0, 0 },
			{ 9717, 2525, 3373, 0, 0 },
			{ 29217, 2526, 3373, 0, 0 },
			{ 29215, 2527, 3373, 0, 0 },
			{ 29215, 2528, 3373, 0, 0 },
			{ 9720, 2529, 3373, 0, 0 },
			{ 9720, 2530, 3373, 0, 0 },
			{ 25368, 2531, 3373, 0, 0 },
			{ 25370, 2532, 3373, 0, 0 },
			{ 11941, 2533, 3373, 0, 0 },
			//furnace & anvil
			{ 6189, 2532, 3370, 0, 2 },
			{ 2783, 2531, 3369, 0, 2 },
			//stalls
			{ 4876, 2527, 3369, 0, 2 },
			{ 4875, 2526, 3369, 0, 2 },
			{ 4874, 2525, 3369, 0, 2 },
			{ 4877, 2524, 3369, 0, 2 },
			{ 4878, 2523, 3369, 0, 2 },

			{ 6552, 3232, 2887, 0, 0 },
			{ 9326, 3001, 3960, 0, 0 },
			{ -1, 2342, 3807, 0, 0 },
			{ -1, 2344, 3809, 0, 0 },
			{ 2783, 2342, 3807, 0, 0 },

			// ezone frost dragon tele
			{47180, 3363, 9640, 0, 0},
			/**
			 * Ezone Skilling Beings
			 **/
			// skilling anvil
			{4306, 3375, 9660, 0, 4},
			// furnace
			{6189, 3376, 9659, 0, 1},
			// bank booths
			// mining booth
			{2213, 3363, 9652, 0, 0},
			// wc booth
			{2213, 3363, 9627, 0, 0}, {2213, 3351, 9640, 0, 3}, {2213, 3376, 9640, 0, 1},
			// mage trees
			{1306, 3353, 9626, 0, 0}, {1306, 3356, 9626, 0, 0}, {1306, 3359, 9626, 0, 0},
			{1306, 3366, 9626, 0, 0}, {1306, 3369, 9626, 0, 0}, {1306, 3372, 9626, 0, 0},
			// coal
			{29215, 3354, 9653, 0, 0}, {29215, 3355, 9653, 0, 0}, {29215, 3356, 9653, 0, 0},
			{29215, 3357, 9653, 0, 0},
			// gold
			{11183, 3358, 9653, 0, 0}, {11183, 3359, 9653, 0, 0},
			// mithril
			{11945, 3360, 9653, 0, 0}, {11945, 3361, 9653, 0, 0},
			// addy
			{11939, 3365, 9653, 0, 0}, {11939, 3366, 9653, 0, 0},
			// rune
			{14859, 3367, 9653, 0, 0}, {14859, 3368, 9653, 0, 0}, {14859, 3369, 9653, 0, 0},
			{14859, 3370, 9653, 0, 0}, {14859, 3371, 9653, 0, 0}, {14859, 3372, 9653, 0, 0},
			{14859, 3373, 9653, 0, 0},
			/**
			 * Ezone Ends
			 **/
			/** New Member Zone */
			{ 2344, 3421, 2908, 0, 0 }, // Rock blocking
			{ 2345, 3438, 2909, 0, 0 }, { 2344, 3435, 2909, 0, 0 }, { 2344, 3432, 2909, 0, 0 },
			{ 2345, 3431, 2909, 0, 0 }, { 2344, 3428, 2921, 0, 1 }, { 2344, 3428, 2918, 0, 1 },
			{ 2344, 3428, 2915, 0, 1 }, { 2344, 3428, 2912, 0, 1 }, { 2345, 3428, 2911, 0, 1 },
			{ 2344, 3417, 2913, 0, 1 }, { 2344, 3417, 2916, 0, 1 }, { 2344, 3417, 2919, 0, 1 },
			{ 2344, 3417, 2922, 0, 1 }, { 2345, 3417, 2912, 0, 0 }, { 2346, 3418, 2925, 0, 0 },
			{ 8749, 3426, 2923, 0, 2 }, // Altar
			{ -1, 3420, 2909, 0, 10 }, // Remove crate by mining
			{ -1, 3420, 2923, 0, 10 }, // Remove Rockslide by Woodcutting
			{ 14859, 3421, 2909, 0, 0 }, // Mining
			{ 14859, 3419, 2909, 0, 0 }, { 14859, 3418, 2910, 0, 0 }, { 14859, 3418, 2911, 0, 0 },
			{ 4483, 2812, 5508, 0, 2 }, // Fun pk bank chest

			{ 14859, 3422, 2909, 0, 0 }, { 1306, 3418, 2921, 0, 0 }, // Woodcutting
			{ 1306, 3421, 2924, 0, 0 }, { 1306, 3420, 2924, 0, 0 }, { 1306, 3419, 2923, 0, 0 },
			{ 1306, 3418, 2922, 0, 0 }, { -1, 3430, 2912, 0, 2 },
			/** New Member Zone end */
			// 1 = west
			// 2 = north
			// 3 = east
			// 4 = south
	};

	public class GameObject {

		public GameObject(int id, int x, int y, int z, int face) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.z = z;
			this.face = face;
		}

		public int id;
		public int x, y, z;
		public int face;
	}

}
