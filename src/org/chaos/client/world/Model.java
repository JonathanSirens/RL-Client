package org.chaos.client.world;

import org.chaos.Configuration;
import org.chaos.client.Class33;
import org.chaos.client.Client;
import org.chaos.client.FrameReader;
import org.chaos.client.ModelHeader;
import org.chaos.client.SkinList;
import org.chaos.client.cache.ondemand.CacheFileRequest;
import org.chaos.client.cache.ondemand.CacheFileRequester;
import org.chaos.client.graphics.Canvas2D;
import org.chaos.client.io.ByteBuffer;
import org.chaos.client.particles.*;
import org.chaos.client.renderable.Animable;

public class Model extends Animable {

	private final void removeColors(int[] colors) {
		if (anIntArray1640 == null) {
			return;
		}
		for (int triangle = 0; triangle < anInt1630; triangle++) {
			if (triangle < anIntArray1640.length) {
				for(int color : colors) {
					if(anIntArray1640[triangle] == color) {
						anIntArray1631[triangle] = 0;
						anIntArray1632[triangle] = 0;
						anIntArray1633[triangle] = 0;
					}
				}
			}
		}
	}
	
	private void filterTriangles() {
	    for (int id = 0; id < anInt1630; id++) {
	        int triA = anIntArray1631[id];
	        int triB = anIntArray1632[id];
	        int triC = anIntArray1633[id];
	        boolean filter = true;
	        label2: for (int id2 = 0; id2 < anInt1630; id2++) {
	        	if (id2 == id) {
                    continue label2;
                }
                if (anIntArray1631[id2] == triA) {
                	filter = false;
                    break label2;
                }
                if (anIntArray1632[id2] == triB) {
                	filter = false;
                    break label2;
                }
                if (anIntArray1633[id2] == triC) {
                	filter = false;
                    break label2;
                }
	        }
	        if (filter) {
	            if (anIntArray1637 != null) {
	                anIntArray1639[id] = 255;
	            }
	        }
	    }
	}
	
	public void convertTexturesTo317(short[] textureIds, int[] texa, int[] texb, int[] texc) {
		int set = 0;
		int set2 = 0;
		int max = 51;
		if(textureIds != null) {
			anIntArray1643 = new int[anInt1630];
			anIntArray1644 = new int[anInt1630];
			anIntArray1645 = new int[anInt1630];
			for(int i = 0; i < anInt1630; i++) {
				
				if(textureIds[i] == -1 && anIntArray1637[i] == 2) {
					anIntArray1640[i] = 65535;
					anIntArray1637[i] = 0;
				}
				
				if(textureIds[i] >= max || textureIds[i] < 0 || textureIds[i] == 39) {
					anIntArray1637[i] = 0;
					continue;
				}
				
				anIntArray1637[i] = 2 + set2;
				set2 += 4;
				int a = anIntArray1631[i];
				int b = anIntArray1632[i];
				int c = anIntArray1633[i];
				anIntArray1640[i] = textureIds[i];
				int texture_type = -1;
				
				if(texture_coordinates != null) {
					texture_type = texture_coordinates[i] & 0xff;
					if(texture_type != 0xff) {
						if(texa[texture_type] >= anIntArray1669.length || texb[texture_type] >= anIntArray1668.length || texc[texture_type] >= anIntArray1670.length) {
							texture_type = -1;
						}
					}		
				}
				
				if(texture_type == 0xff) {
					texture_type = -1;
				}
				
				anIntArray1643[set] = texture_type == -1 ? a : texa[texture_type];
				anIntArray1644[set] = texture_type == -1 ? b : texb[texture_type];
				anIntArray1645[set++] = texture_type == -1 ? c : texc[texture_type];
			}
			anInt1642 = set;
		}
	}

	public static void nullLoader() {
		vertexPerspectiveDepth = null;
		aClass21Array1661 = null;
		aBooleanArray1663 = null;
		aBooleanArray1664 = null;
		anIntArray1666 = null;
		anIntArray1667 = null;
		anIntArray1668 = null;
		anIntArray1669 = null;
		anIntArray1670 = null;
		anIntArray1671 = null;
		anIntArrayArray1672 = null;
		anIntArray1673 = null;
		anIntArrayArray1674 = null;
		anIntArray1675 = null;
		anIntArray1676 = null;
		anIntArray1677 = null;
		modelIntArray1 = null;// SINE
		modelIntArray2 = null;// COSIN
		modelIntArray3 = null;
		modelIntArray4 = null;
	}

	public void read525Model(byte abyte0[], int modelID) {
		ByteBuffer nc1 = new ByteBuffer(abyte0);
		ByteBuffer nc2 = new ByteBuffer(abyte0);
		ByteBuffer nc3 = new ByteBuffer(abyte0);
		ByteBuffer nc4 = new ByteBuffer(abyte0);
		ByteBuffer nc5 = new ByteBuffer(abyte0);
		ByteBuffer nc6 = new ByteBuffer(abyte0);
		ByteBuffer nc7 = new ByteBuffer(abyte0);
		nc1.position = abyte0.length - 23;
		int numVertices = nc1.getUnsignedShort();
		int numTriangles = nc1.getUnsignedShort();
		int numTexTriangles = nc1.getUnsignedByte();
		ModelHeader ModelDef_1 = aClass21Array1661[modelID] = new ModelHeader();
		ModelDef_1.aByteArray368 = abyte0;
		ModelDef_1.anInt369 = numVertices;
		ModelDef_1.anInt370 = numTriangles;
		ModelDef_1.anInt371 = numTexTriangles;
		int l1 = nc1.getUnsignedByte();
		boolean bool = (0x1 & l1 ^ 0xffffffff) == -2;
		int i2 = nc1.getUnsignedByte();
		int j2 = nc1.getUnsignedByte();
		int k2 = nc1.getUnsignedByte();
		int l2 = nc1.getUnsignedByte();
		int i3 = nc1.getUnsignedByte();
		int j3 = nc1.getUnsignedShort();
		int k3 = nc1.getUnsignedShort();
		int l3 = nc1.getUnsignedShort();
		int i4 = nc1.getUnsignedShort();
		int j4 = nc1.getUnsignedShort();
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		byte[] textureCoordinates = null;
		byte[] O = null;
		byte[] J = null;
		byte[] F = null;
		byte[] cb = null;
		byte[] gb = null;
		byte[] lb = null;
		int[] kb = null;
		int[] y = null;
		int[] N = null;
		short[] textureIds = null;
		int[] triangleColours2 = new int[numTriangles];
		if (numTexTriangles > 0) {
			O = new byte[numTexTriangles];
			nc1.position = 0;
			for (int j5 = 0; j5 < numTexTriangles; j5++) {
				byte byte0 = O[j5] = nc1.getSignedByte();
				if (byte0 == 0)
					k4++;
				if (byte0 >= 1 && byte0 <= 3)
					l4++;
				if (byte0 == 2)
					i5++;
			}
		}
		int k5 = numTexTriangles;
		int l5 = k5;
		k5 += numVertices;
		int i6 = k5;
		if (l1 == 1)
			k5 += numTriangles;
		int j6 = k5;
		k5 += numTriangles;
		int k6 = k5;
		if (i2 == 255)
			k5 += numTriangles;
		int l6 = k5;
		if (k2 == 1)
			k5 += numTriangles;
		int i7 = k5;
		if (i3 == 1)
			k5 += numVertices;
		int j7 = k5;
		if (j2 == 1)
			k5 += numTriangles;
		int k7 = k5;
		k5 += i4;
		int l7 = k5;
		if (l2 == 1)
			k5 += numTriangles * 2;
		int i8 = k5;
		k5 += j4;
		int j8 = k5;
		k5 += numTriangles * 2;
		int k8 = k5;
		k5 += j3;
		int l8 = k5;
		k5 += k3;
		int i9 = k5;
		k5 += l3;
		int j9 = k5;
		k5 += k4 * 6;
		int k9 = k5;
		k5 += l4 * 6;
		int l9 = k5;
		k5 += l4 * 6;
		int i10 = k5;
		k5 += l4;
		int j10 = k5;
		k5 += l4;
		int k10 = k5;
		k5 += l4 + i5 * 2;
		int[] vertexX = new int[numVertices];
		int[] vertexY = new int[numVertices];
		int[] vertexZ = new int[numVertices];
		int[] facePoint1 = new int[numTriangles];
		int[] facePoint2 = new int[numTriangles];
		int[] facePoint3 = new int[numTriangles];
		this.modelParticles = new int[numVertices];// 525
		anIntArray1655 = new int[numVertices];
		anIntArray1637 = new int[numTriangles];
		anIntArray1638 = new int[numTriangles];
		anIntArray1639 = new int[numTriangles];
		anIntArray1656 = new int[numTriangles];
		if (i3 == 1)
			anIntArray1655 = new int[numVertices];
		if (bool)
			anIntArray1637 = new int[numTriangles];
		if (i2 == 255)
			anIntArray1638 = new int[numTriangles];
		if (j2 == 1)
			anIntArray1639 = new int[numTriangles];
		if (k2 == 1)
			anIntArray1656 = new int[numTriangles];
		if (l2 == 1)
			textureIds = new short[numTriangles];
		if (l2 == 1 && numTexTriangles > 0)
			textureCoordinates = texture_coordinates = new byte[numTriangles];
		triangleColours2 = new int[numTriangles];
		int[] texTrianglesPoint1 = null;
		int[] texTrianglesPoint2 = null;
		int[] texTrianglesPoint3 = null;
		if (numTexTriangles > 0) {
			texTrianglesPoint1 = new int[numTexTriangles];
			texTrianglesPoint2 = new int[numTexTriangles];
			texTrianglesPoint3 = new int[numTexTriangles];
			if (l4 > 0) {
				kb = new int[l4];
				N = new int[l4];
				y = new int[l4];
				gb = new byte[l4];
				lb = new byte[l4];
				F = new byte[l4];
			}
			if (i5 > 0) {
				cb = new byte[i5];
				J = new byte[i5];
			}
		}
		nc1.position = l5;
		nc2.position = k8;
		nc3.position = l8;
		nc4.position = i9;
		nc5.position = i7;
		int l10 = 0;
		int i11 = 0;
		int j11 = 0;
		for (int k11 = 0; k11 < numVertices; k11++) {
			int l11 = nc1.getUnsignedByte();
			int j12 = 0;
			if ((l11 & 1) != 0)
				j12 = nc2.getUnsignedSmart();
			int l12 = 0;
			if ((l11 & 2) != 0)
				l12 = nc3.getUnsignedSmart();
			int j13 = 0;
			if ((l11 & 4) != 0)
				j13 = nc4.getUnsignedSmart();
			vertexX[k11] = l10 + j12;
			vertexY[k11] = i11 + l12;
			vertexZ[k11] = j11 + j13;
			l10 = vertexX[k11];
			i11 = vertexY[k11];
			j11 = vertexZ[k11];
			if (anIntArray1655 != null)
				anIntArray1655[k11] = nc5.getUnsignedByte();
		}
		nc1.position = j8;
		nc2.position = i6;
		nc3.position = k6;
		nc4.position = j7;
		nc5.position = l6;
		nc6.position = l7;
		nc7.position = i8;
		for (int i12 = 0; i12 < numTriangles; i12++) {
			triangleColours2[i12] = nc1.getUnsignedShort();
			if (l1 == 1) {
				anIntArray1637[i12] = nc2.getSignedByte();
				if (anIntArray1637[i12] >= 2 && textureIds != null) {
					triangleColours2[i12] = 65535;
				} else {
					anIntArray1637[i12] = 0;
				}
				
			}
			if (i2 == 255) {
				anIntArray1638[i12] = nc3.getSignedByte();
			}
			if (j2 == 1) {
				anIntArray1639[i12] = nc4.getSignedByte();
				if (anIntArray1639[i12] < 0)
					anIntArray1639[i12] = (256 + anIntArray1639[i12]);
			}
			if (k2 == 1)
				anIntArray1656[i12] = nc5.getUnsignedByte();
			if (l2 == 1)
				textureIds[i12] = (short) (nc6.getUnsignedShort() - 1);
			
			if (textureCoordinates != null) {
				if (textureIds[i12] != -1) {
					textureCoordinates[i12] = texture_coordinates[i12] = (byte) (nc7.getUnsignedByte() - 1);
				} else {
					textureCoordinates[i12] = texture_coordinates[i12] = -1;
				}
			}
		}
		nc1.position = k7;
		nc2.position = j6;
		int k12 = 0;
		int i13 = 0;
		int k13 = 0;
		int l13 = 0;
		for (int i14 = 0; i14 < numTriangles; i14++) {
			int j14 = nc2.getUnsignedByte();
			if (j14 == 1) {
				k12 = nc1.getUnsignedSmart() + l13;
				l13 = k12;
				i13 = nc1.getUnsignedSmart() + l13;
				l13 = i13;
				k13 = nc1.getUnsignedSmart() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 2) {
				i13 = k13;
				k13 = nc1.getUnsignedSmart() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 3) {
				k12 = k13;
				k13 = nc1.getUnsignedSmart() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 4) {
				int l14 = k12;
				k12 = i13;
				i13 = l14;
				k13 = nc1.getUnsignedSmart() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
		}
		nc1.position = j9;
		nc2.position = k9;
		nc3.position = l9;
		nc4.position = i10;
		nc5.position = j10;
		nc6.position = k10;
		for (int k14 = 0; k14 < numTexTriangles; k14++) {
			int i15 = O[k14] & 0xff;
			if (i15 == 0) {
				texTrianglesPoint1[k14] = nc1.getUnsignedShort();
				texTrianglesPoint2[k14] = nc1.getUnsignedShort();
				texTrianglesPoint3[k14] = nc1.getUnsignedShort();
			}
			if (i15 == 1) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();
				kb[k14] = nc3.getUnsignedShort();
				N[k14] = nc3.getUnsignedShort();
				y[k14] = nc3.getUnsignedShort();
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
			}
			if (i15 == 2) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();
				kb[k14] = nc3.getUnsignedShort();
				N[k14] = nc3.getUnsignedShort();
				y[k14] = nc3.getUnsignedShort();
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
				cb[k14] = nc6.getSignedByte();
				J[k14] = nc6.getSignedByte();
			}
			if (i15 == 3) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();
				kb[k14] = nc3.getUnsignedShort();
				N[k14] = nc3.getUnsignedShort();
				y[k14] = nc3.getUnsignedShort();
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
			}
		}
		if (i2 != 255) {
			for (int i12 = 0; i12 < numTriangles; i12++)
				anIntArray1638[i12] = i2;
		}
		anIntArray1640 = triangleColours2;
		anInt1626 = numVertices;
		anInt1630 = numTriangles;
		anIntArray1627 = vertexX;
		anIntArray1628 = vertexY;
		anIntArray1629 = vertexZ;
		anIntArray1631 = facePoint1;
		anIntArray1632 = facePoint2;
		anIntArray1633 = facePoint3;
		convertTexturesTo317(textureIds, texTrianglesPoint1, texTrianglesPoint2, texTrianglesPoint3);
		filterTriangles();
		//Used to increase the size of OSRS dialog models
		switch(modelID) {
			case 10417:
			case 10418:
			case 48:
			case 83:
			case 10419:
			case 28855:
			case 28856:
			case 28857:
			case 28858:
			case 28859:
			case 28860:
			case 28861:
			case 28862:
			case 28864:
			case 28865:
			case 28866:
			case 29184:
			case 29185:
			case 29186:
			case 29187:
			case 29320:
			case 29758:
			case 28761:
			case 29762:
			case 5805:
				upscale(1);
				break;
		}
	}

	public Model(int modelId) {
		byte[] is = aClass21Array1661[modelId].aByteArray368;
		if (is[is.length - 1] == -1 && is[is.length - 2] == -1)
			read622Model(is, modelId);
		else
			readOldModel(modelId);

		//System.out.println(""+modelId);
		/*if (newmodel[modelId]) {
			if (anIntArray1638 != null) {
				for (int index = 0; index < anIntArray1638.length; index++) {
					anIntArray1638[index] = 10;
				}
			}
		}*/
		
		int[] newBoots = new int[] {29249, 29254, 29250, 29255, 29252, 29253};
		for(int i : newBoots) {
			if (modelId == i)
				for (int j = 0; j < anIntArray1638.length; j++) {
					anIntArray1638[j] = 10;
				}
		}
		if ((modelId >= 53347 && modelId <= 53370) || (modelId >= 76001 && modelId <= 76047)) {
			// recolour(0, 255);
			if (anIntArray1638 != null) { // rofl
				for (int j = 0; j < anIntArray1638.length; j++)
					anIntArray1638[j] = 10;
			}
		}

		int[][] particleConfiguration = ParticleConfiguration.getParticlesForModel(modelId);
		int var4;
		if (particleConfiguration != null) {
			for (var4 = 0; var4 < particleConfiguration.length; var4++) {
				int[] var5 = particleConfiguration[var4];
				int var6;
				if (var5[0] == -1) {
					for (var6 = 0; var6 < anIntArray1631.length; ++var6) {
						modelParticles[anIntArray1631[var6]] = var5[1] + 1;
					}
				} else if (var5[0] == -2) {
					for (var6 = 0; var6 < anIntArray1632.length; ++var6) {
						modelParticles[anIntArray1632[var6]] = var5[1] + 1;
					}
				} else if (var5[0] == -3) {
					for (var6 = 0; var6 < anIntArray1633.length; ++var6) {
						modelParticles[anIntArray1633[var6]] = var5[1] + 1;
					}
				} else if (var5[0] == -4) {
					for (var6 = 0; var6 < anIntArray1631.length; ++var6) {
						modelParticles[anIntArray1631[var6]] = var5[1] + 1;
					}

					for (var6 = 0; var6 < anIntArray1632.length; ++var6) {
						modelParticles[anIntArray1632[var6]] = var5[1] + 1;
					}

					for (var6 = 0; var6 < anIntArray1633.length; ++var6) {
						modelParticles[anIntArray1633[var6]] = var5[1] + 1;
					}
				} else {
					modelParticles[var5[0]] = var5[1] + 1;
				}
			}
		}
	}

	public void scale2(int i) {
		for (int i1 = 0; i1 < anInt1626; i1++) {
			anIntArray1627[i1] = anIntArray1627[i1] / i;
			anIntArray1628[i1] = anIntArray1628[i1] / i;
			anIntArray1629[i1] = anIntArray1629[i1] / i;
		}
	}

	public void downscale() {
		for (int i = 0; i != anInt1626; ++i) {
			anIntArray1627[i] = (anIntArray1627[i] + 7) >> 3;
			anIntArray1628[i] = (anIntArray1628[i] + 7) >> 3;
			anIntArray1629[i] = (anIntArray1629[i] + 7) >> 3;
		}
	}

	public void upscale(int size) {
		for (int i = 0; i < anInt1626; i++) {
			anIntArray1627[i] <<= size;
			anIntArray1628[i] <<= size;
			anIntArray1629[i] <<= size;
		}
	}

	public void applyTransform(int i) {
		if (anIntArrayArray1657 == null) {
			return;
		}
		if (i == -1) {
			return;
		}
		FrameReader class36 = FrameReader.forId(i);
		if (class36 == null) {
			return;
		}
		SkinList class18 = class36.skinList;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		for (int k = 0; k < class36.stepCount; k++) {
			int l = class36.opcodeLinkTable[k];
			method472(class18.opcodes[l], class18.skinList[l], class36.xOffset[k], class36.yOffset[k],
					class36.zOffset[k]);
		}

	}

	public void interpolateFrames(int firstFrame, int nextFrame, int end, int cycle) {
		if (!Configuration.TWEENING_ENABLED) {
			applyTransform(nextFrame);
			return;
		}
		try {
			if (anIntArrayArray1657 != null && firstFrame != -1) {
				FrameReader currentAnimation = FrameReader.forId(firstFrame);
				if (currentAnimation == null) {
					applyTransform(nextFrame);
					return;
				}
				SkinList list1 = currentAnimation.skinList;
				anInt1681 = 0;
				anInt1682 = 0;
				anInt1683 = 0;
				FrameReader nextAnimation = null;
				SkinList list2 = null;
				if (nextFrame != -1) {
					nextAnimation = FrameReader.forId(nextFrame);
					if (nextAnimation.skinList != list1)
						nextAnimation = null;
					list2 = nextAnimation.skinList;
				}
				if (nextAnimation == null || list2 == null) {
					for (int i_263_ = 0; i_263_ < currentAnimation.stepCount; i_263_++) {
						int i_264_ = currentAnimation.opcodeLinkTable[i_263_];
						method472(list1.opcodes[i_264_], list1.skinList[i_264_], currentAnimation.xOffset[i_263_],
								currentAnimation.yOffset[i_263_], currentAnimation.zOffset[i_263_]);

					}
				} else {
					for (int i1 = 0; i1 < currentAnimation.stepCount; i1++) {
						int n1 = currentAnimation.opcodeLinkTable[i1];
						int opcode = list1.opcodes[n1];
						int[] skin = list1.skinList[n1];
						int x = currentAnimation.xOffset[i1];
						int y = currentAnimation.yOffset[i1];
						int z = currentAnimation.zOffset[i1];
						boolean found = false;
						for (int i2 = 0; i2 < nextAnimation.stepCount; i2++) {
							int n2 = nextAnimation.opcodeLinkTable[i2];
							if (list2.skinList[n2].equals(skin)) {
								if (opcode != 2) {
									x += (nextAnimation.xOffset[i2] - x) * cycle / end;
									y += (nextAnimation.yOffset[i2] - y) * cycle / end;
									z += (nextAnimation.zOffset[i2] - z) * cycle / end;
								} else {
									x &= 0xff;
									y &= 0xff;
									z &= 0xff;
									int dx = nextAnimation.xOffset[i2] - x & 0xff;
									int dy = nextAnimation.yOffset[i2] - y & 0xff;
									int dz = nextAnimation.zOffset[i2] - z & 0xff;
									if (dx >= 128) {
										dx -= 256;
									}
									if (dy >= 128) {
										dy -= 256;
									}
									if (dz >= 128) {
										dz -= 256;
									}
									x = x + dx * cycle / end & 0xff;
									y = y + dy * cycle / end & 0xff;
									z = z + dz * cycle / end & 0xff;
								}
								found = true;
								break;
							}
						}
						if (!found) {
							if (opcode != 3 && opcode != 2) {
								x = x * (end - cycle) / end;
								y = y * (end - cycle) / end;
								z = z * (end - cycle) / end;
							} else if (opcode == 3) {
								x = (x * (end - cycle) + (cycle << 7)) / end;
								y = (y * (end - cycle) + (cycle << 7)) / end;
								z = (z * (end - cycle) + (cycle << 7)) / end;
							} else {
								x &= 0xff;
								y &= 0xff;
								z &= 0xff;
								int dx = -x & 0xff;
								int dy = -y & 0xff;
								int dz = -z & 0xff;
								if (dx >= 128) {
									dx -= 256;
								}
								if (dy >= 128) {
									dy -= 256;
								}
								if (dz >= 128) {
									dz -= 256;
								}
								x = x + dx * cycle / end & 0xff;
								y = y + dy * cycle / end & 0xff;
								z = z + dz * cycle / end & 0xff;
							}
						}
						method472(opcode, skin, x, y, z);
					}
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
			applyTransform(firstFrame);
		}
	}

	public void read622Model(byte abyte0[], int modelID) {
		ByteBuffer nc1 = new ByteBuffer(abyte0);
		ByteBuffer nc2 = new ByteBuffer(abyte0);
		ByteBuffer nc3 = new ByteBuffer(abyte0);
		ByteBuffer nc4 = new ByteBuffer(abyte0);
		ByteBuffer nc5 = new ByteBuffer(abyte0);
		ByteBuffer nc6 = new ByteBuffer(abyte0);
		ByteBuffer nc7 = new ByteBuffer(abyte0);
		nc1.position = abyte0.length - 23;
		int numVertices = nc1.getUnsignedShort();
		int numTriangles = nc1.getUnsignedShort();
		int numTexTriangles = nc1.getUnsignedByte();
		ModelHeader ModelDef_1 = aClass21Array1661[modelID] = new ModelHeader();
		ModelDef_1.aByteArray368 = abyte0;
		ModelDef_1.anInt369 = numVertices;
		ModelDef_1.anInt370 = numTriangles;
		ModelDef_1.anInt371 = numTexTriangles;
		int l1 = nc1.getUnsignedByte();
		boolean bool = (0x1 & l1 ^ 0xffffffff) == -2;
		boolean bool_26_ = (0x8 & l1) == 8;
		if (!bool_26_) {
			read525Model(abyte0, modelID);
			return;
		}
		int newformat = 0;
		if (bool_26_) {
			nc1.position -= 7;
			newformat = nc1.getUnsignedByte();
			nc1.position += 6;
		}
		if (newformat == 15)
			newmodel[modelID] = true;
		int i2 = nc1.getUnsignedByte();
		int j2 = nc1.getUnsignedByte();
		int k2 = nc1.getUnsignedByte();
		int l2 = nc1.getUnsignedByte();
		int i3 = nc1.getUnsignedByte();
		int j3 = nc1.getUnsignedShort();
		int k3 = nc1.getUnsignedShort();
		int l3 = nc1.getUnsignedShort();
		int i4 = nc1.getUnsignedShort();
		int j4 = nc1.getUnsignedShort();
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		byte[] textureCoordinates = null;
		byte[] O = null;
		byte[] J = null;
		byte[] F = null;
		byte[] cb = null;
		byte[] gb = null;
		byte[] lb = null;
		int[] kb = null;
		int[] y = null;
		int[] N = null;
		short[] textureIds = null;
		int[] triangleColours2 = new int[numTriangles];
		if (numTexTriangles > 0) {
			O = new byte[numTexTriangles];
			nc1.position = 0;
			for (int j5 = 0; j5 < numTexTriangles; j5++) {
				byte byte0 = O[j5] = nc1.getSignedByte();
				if (byte0 == 0)
					k4++;
				if (byte0 >= 1 && byte0 <= 3)
					l4++;
				if (byte0 == 2)
					i5++;
			}
		}
		int k5 = numTexTriangles;
		int l5 = k5;
		k5 += numVertices;
		int i6 = k5;
		if (bool)
			k5 += numTriangles;
		if (l1 == 1)
			k5 += numTriangles;
		int j6 = k5;
		k5 += numTriangles;
		int k6 = k5;
		if (i2 == 255)
			k5 += numTriangles;
		int l6 = k5;
		if (k2 == 1)
			k5 += numTriangles;
		int i7 = k5;
		if (i3 == 1)
			k5 += numVertices;
		int j7 = k5;
		if (j2 == 1)
			k5 += numTriangles;
		int k7 = k5;
		k5 += i4;
		int l7 = k5;
		if (l2 == 1)
			k5 += numTriangles * 2;
		int i8 = k5;
		k5 += j4;
		int j8 = k5;
		k5 += numTriangles * 2;
		int k8 = k5;
		k5 += j3;
		int l8 = k5;
		k5 += k3;
		int i9 = k5;
		k5 += l3;
		int j9 = k5;
		k5 += k4 * 6;
		int k9 = k5;
		k5 += l4 * 6;
		int i_59_ = 6;
		if (newformat != 14) {
			if (newformat >= 15)
				i_59_ = 9;
		} else
			i_59_ = 7;
		int l9 = k5;
		k5 += i_59_ * l4;
		int i10 = k5;
		k5 += l4;
		int j10 = k5;
		k5 += l4;
		int k10 = k5;
		k5 += l4 + i5 * 2;
		int[] vertexX = new int[numVertices];
		int[] vertexY = new int[numVertices];
		int[] vertexZ = new int[numVertices];
		int[] facePoint1 = new int[numTriangles];
		int[] facePoint2 = new int[numTriangles];
		int[] facePoint3 = new int[numTriangles];
		this.modelParticles = new int[numVertices];// 622
		anIntArray1655 = new int[numVertices];
		anIntArray1637 = new int[numTriangles];
		anIntArray1638 = new int[numTriangles];
		anIntArray1639 = new int[numTriangles];
		anIntArray1656 = new int[numTriangles];
		if (i3 == 1)
			anIntArray1655 = new int[numVertices];
		if (bool)
			anIntArray1637 = new int[numTriangles];
		if (i2 == 255)
			anIntArray1638 = new int[numTriangles];
		if (j2 == 1)
			anIntArray1639 = new int[numTriangles];
		if (k2 == 1)
			anIntArray1656 = new int[numTriangles];
		if (l2 == 1)
			textureIds = new short[numTriangles];
		if (l2 == 1 && numTexTriangles > 0)
			textureCoordinates = texture_coordinates = new byte[numTriangles];
		triangleColours2 = new int[numTriangles];
		int[] texTrianglesPoint1 = null;
		int[] texTrianglesPoint2 = null;
		int[] texTrianglesPoint3 = null;
		if (numTexTriangles > 0) {
			texTrianglesPoint1 = new int[numTexTriangles];
			texTrianglesPoint2 = new int[numTexTriangles];
			texTrianglesPoint3 = new int[numTexTriangles];
			if (l4 > 0) {
				kb = new int[l4];
				N = new int[l4];
				y = new int[l4];
				gb = new byte[l4];
				lb = new byte[l4];
				F = new byte[l4];
			}
			if (i5 > 0) {
				cb = new byte[i5];
				J = new byte[i5];
			}
		}
		nc1.position = l5;
		nc2.position = k8;
		nc3.position = l8;
		nc4.position = i9;
		nc5.position = i7;
		int l10 = 0;
		int i11 = 0;
		int j11 = 0;
		for (int k11 = 0; k11 < numVertices; k11++) {
			int l11 = nc1.getUnsignedByte();
			int j12 = 0;
			if ((l11 & 1) != 0)
				j12 = nc2.getUnsignedSmart();
			int l12 = 0;
			if ((l11 & 2) != 0)
				l12 = nc3.getUnsignedSmart();
			int j13 = 0;
			if ((l11 & 4) != 0)
				j13 = nc4.getUnsignedSmart();
			vertexX[k11] = l10 + j12;
			vertexY[k11] = i11 + l12;
			vertexZ[k11] = j11 + j13;
			l10 = vertexX[k11];
			i11 = vertexY[k11];
			j11 = vertexZ[k11];
			if (anIntArray1655 != null)
				anIntArray1655[k11] = nc5.getUnsignedByte();
		}
		nc1.position = j8;
		nc2.position = i6;
		nc3.position = k6;
		nc4.position = j7;
		nc5.position = l6;
		nc6.position = l7;
		nc7.position = i8;
		for (int i12 = 0; i12 < numTriangles; i12++) {
			triangleColours2[i12] = nc1.getUnsignedShort();
			if (l1 == 1) {
				anIntArray1637[i12] = nc2.getSignedByte();
				if (anIntArray1637[i12] >= 2 && textureIds != null) {
					triangleColours2[i12] = 65535;
				} else {
					anIntArray1637[i12] = 0;
				}
			}
			if (i2 == 255) {
				anIntArray1638[i12] = nc3.getSignedByte();
			}
			if (j2 == 1) {
				anIntArray1639[i12] = nc4.getSignedByte();
				if (anIntArray1639[i12] < 0)
					anIntArray1639[i12] = (256 + anIntArray1639[i12]);
			}
			if (k2 == 1)
				anIntArray1656[i12] = nc5.getUnsignedByte();
			if (l2 == 1)
				textureIds[i12] = (short) (nc6.getUnsignedShort() - 1);
			if (textureCoordinates != null)
				if (textureIds[i12] != -1)
					textureCoordinates[i12] = texture_coordinates[i12] = (byte) (nc7.getUnsignedByte() - 1);
				else
					textureCoordinates[i12] = texture_coordinates[i12] = -1;
		}
		nc1.position = k7;
		nc2.position = j6;
		int k12 = 0;
		int i13 = 0;
		int k13 = 0;
		int l13 = 0;
		for (int i14 = 0; i14 < numTriangles; i14++) {
			int j14 = nc2.getUnsignedByte();
			if (j14 == 1) {
				k12 = nc1.getUnsignedSmart() + l13;
				l13 = k12;
				i13 = nc1.getUnsignedSmart() + l13;
				l13 = i13;
				k13 = nc1.getUnsignedSmart() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 2) {
				i13 = k13;
				k13 = nc1.getUnsignedSmart() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 3) {
				k12 = k13;
				k13 = nc1.getUnsignedSmart() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
			if (j14 == 4) {
				int l14 = k12;
				k12 = i13;
				i13 = l14;
				k13 = nc1.getUnsignedSmart() + l13;
				l13 = k13;
				facePoint1[i14] = k12;
				facePoint2[i14] = i13;
				facePoint3[i14] = k13;
			}
		}
		nc1.position = j9;
		nc2.position = k9;
		nc3.position = l9;
		nc4.position = i10;
		nc5.position = j10;
		nc6.position = k10;
		for (int k14 = 0; k14 < numTexTriangles; k14++) {
			int i15 = O[k14] & 0xff;
			if (i15 == 0) {
				texTrianglesPoint1[k14] = nc1.getUnsignedShort();
				texTrianglesPoint2[k14] = nc1.getUnsignedShort();
				texTrianglesPoint3[k14] = nc1.getUnsignedShort();
			}
			if (i15 == 1) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();
				if (newformat < 15) {
					kb[k14] = nc3.getUnsignedShort();
					if (newformat >= 14)
						N[k14] = nc3.getMediumInt(-1);
					else
						N[k14] = nc3.getUnsignedShort();
					y[k14] = nc3.getUnsignedShort();
				} else {
					kb[k14] = nc3.getMediumInt(-1);
					N[k14] = nc3.getMediumInt(-1);
					y[k14] = nc3.getMediumInt(-1);
				}
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
			}
			if (i15 == 2) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();
				if (newformat >= 15) {
					kb[k14] = nc3.getMediumInt(-1);
					N[k14] = nc3.getMediumInt(-1);
					y[k14] = nc3.getMediumInt(-1);
				} else {
					kb[k14] = nc3.getUnsignedShort();
					if (newformat < 14)
						N[k14] = nc3.getUnsignedShort();
					else
						N[k14] = nc3.getMediumInt(-1);
					y[k14] = nc3.getUnsignedShort();
				}
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
				cb[k14] = nc6.getSignedByte();
				J[k14] = nc6.getSignedByte();
			}
			if (i15 == 3) {
				texTrianglesPoint1[k14] = nc2.getUnsignedShort();
				texTrianglesPoint2[k14] = nc2.getUnsignedShort();
				texTrianglesPoint3[k14] = nc2.getUnsignedShort();
				if (newformat < 15) {
					kb[k14] = nc3.getUnsignedShort();
					if (newformat < 14)
						N[k14] = nc3.getUnsignedShort();
					else
						N[k14] = nc3.getMediumInt(-1);
					y[k14] = nc3.getUnsignedShort();
				} else {
					kb[k14] = nc3.getMediumInt(-1);
					N[k14] = nc3.getMediumInt(-1);
					y[k14] = nc3.getMediumInt(-1);
				}
				gb[k14] = nc4.getSignedByte();
				lb[k14] = nc5.getSignedByte();
				F[k14] = nc6.getSignedByte();
			}
		}
		if (i2 != 255) {
			for (int i12 = 0; i12 < numTriangles; i12++) {
				anIntArray1638[i12] = i2;
			}
		}
		anIntArray1640 = triangleColours2;
		anInt1626 = numVertices;
		anInt1630 = numTriangles;
		anIntArray1627 = vertexX;
		anIntArray1628 = vertexY;
		anIntArray1629 = vertexZ;
		anIntArray1631 = facePoint1;
		anIntArray1632 = facePoint2;
		anIntArray1633 = facePoint3;
		upscale(1);
		downscale();
		convertTexturesTo317(textureIds, texTrianglesPoint1, texTrianglesPoint2, texTrianglesPoint3);
		filterTriangles();
		if (anIntArray1638 != null) {
			for (int j = 0; j < anIntArray1638.length; j++) {
				anIntArray1638[j] = 10;
			}
		}
	}

	private void readOldModel(int i) {

		int j = -870;
		aBoolean1618 = true;
		aBoolean1659 = false;
		anInt1620++;
		ModelHeader class21 = aClass21Array1661[i];
		anInt1626 = class21.anInt369;
		anInt1630 = class21.anInt370;
		anInt1642 = class21.anInt371;
		modelParticles = new int[anInt1626];
		anIntArray1627 = new int[anInt1626];
		anIntArray1628 = new int[anInt1626];
		anIntArray1629 = new int[anInt1626];
		anIntArray1631 = new int[anInt1630];
		anIntArray1632 = new int[anInt1630];
		while (j >= 0)
			aBoolean1618 = !aBoolean1618;
		anIntArray1633 = new int[anInt1630];
		anIntArray1643 = new int[anInt1642];
		anIntArray1644 = new int[anInt1642];
		anIntArray1645 = new int[anInt1642];
		if (class21.anInt376 >= 0)
			anIntArray1655 = new int[anInt1626];
		if (class21.anInt380 >= 0)
			anIntArray1637 = new int[anInt1630];
		if (class21.anInt381 >= 0)
			anIntArray1638 = new int[anInt1630];
		else
			anInt1641 = -class21.anInt381 - 1;
		if (class21.anInt382 >= 0)
			anIntArray1639 = new int[anInt1630];
		if (class21.anInt383 >= 0)
			anIntArray1656 = new int[anInt1630];
		anIntArray1640 = new int[anInt1630];
		ByteBuffer stream = new ByteBuffer(class21.aByteArray368);
		stream.position = class21.anInt372;
		ByteBuffer stream_1 = new ByteBuffer(class21.aByteArray368);
		stream_1.position = class21.anInt373;
		ByteBuffer stream_2 = new ByteBuffer(class21.aByteArray368);
		stream_2.position = class21.anInt374;
		ByteBuffer stream_3 = new ByteBuffer(class21.aByteArray368);
		stream_3.position = class21.anInt375;
		ByteBuffer stream_4 = new ByteBuffer(class21.aByteArray368);
		stream_4.position = class21.anInt376;
		int k = 0;
		int l = 0;
		int i1 = 0;
		for (int j1 = 0; j1 < anInt1626; j1++) {
			int k1 = stream.getUnsignedByte();
			int i2 = 0;
			if ((k1 & 1) != 0)
				i2 = stream_1.getUnsignedSmart();
			int k2 = 0;
			if ((k1 & 2) != 0)
				k2 = stream_2.getUnsignedSmart();
			int i3 = 0;
			if ((k1 & 4) != 0)
				i3 = stream_3.getUnsignedSmart();
			anIntArray1627[j1] = k + i2;
			anIntArray1628[j1] = l + k2;
			anIntArray1629[j1] = i1 + i3;
			k = anIntArray1627[j1];
			l = anIntArray1628[j1];
			i1 = anIntArray1629[j1];
			if (anIntArray1655 != null)
				anIntArray1655[j1] = stream_4.getUnsignedByte();
		}
		stream.position = class21.anInt379;
		stream_1.position = class21.anInt380;
		stream_2.position = class21.anInt381;
		stream_3.position = class21.anInt382;
		stream_4.position = class21.anInt383;
		for (int l1 = 0; l1 < anInt1630; l1++) {
			anIntArray1640[l1] = stream.getUnsignedShort();
			if (anIntArray1637 != null)
				anIntArray1637[l1] = stream_1.getUnsignedByte();
			if (anIntArray1638 != null)
				anIntArray1638[l1] = stream_2.getUnsignedByte();
			if (anIntArray1639 != null) {
				anIntArray1639[l1] = stream_3.getUnsignedByte();
			}
			if (anIntArray1656 != null)
				anIntArray1656[l1] = stream_4.getUnsignedByte();
		}
		stream.position = class21.anInt377;
		stream_1.position = class21.anInt378;
		int j2 = 0;
		int l2 = 0;
		int j3 = 0;
		int k3 = 0;
		for (int l3 = 0; l3 < anInt1630; l3++) {
			int i4 = stream_1.getUnsignedByte();
			if (i4 == 1) {
				j2 = stream.getUnsignedSmart() + k3;
				k3 = j2;
				l2 = stream.getUnsignedSmart() + k3;
				k3 = l2;
				j3 = stream.getUnsignedSmart() + k3;
				k3 = j3;
				anIntArray1631[l3] = j2;
				anIntArray1632[l3] = l2;
				anIntArray1633[l3] = j3;
			}
			if (i4 == 2) {
				l2 = j3;
				j3 = stream.getUnsignedSmart() + k3;
				k3 = j3;
				anIntArray1631[l3] = j2;
				anIntArray1632[l3] = l2;
				anIntArray1633[l3] = j3;
			}
			if (i4 == 3) {
				j2 = j3;
				j3 = stream.getUnsignedSmart() + k3;
				k3 = j3;
				anIntArray1631[l3] = j2;
				anIntArray1632[l3] = l2;
				anIntArray1633[l3] = j3;
			}
			if (i4 == 4) {
				int k4 = j2;
				j2 = l2;
				l2 = k4;
				j3 = stream.getUnsignedSmart() + k3;
				k3 = j3;
				anIntArray1631[l3] = j2;
				anIntArray1632[l3] = l2;
				anIntArray1633[l3] = j3;
			}
		}
		stream.position = class21.anInt384;
		for (int j4 = 0; j4 < anInt1642; j4++) {
			anIntArray1643[j4] = stream.getUnsignedShort();
			anIntArray1644[j4] = stream.getUnsignedShort();
			anIntArray1645[j4] = stream.getUnsignedShort();
		}
		switch(i) {
			case 48:
			case 83:
            case 64:
            case 73:
            case 58:
            case 84:
            case 43:
            case 2925:
            case 10031:
				upscale(1);
				break;
		}
	}

	public static void method460(byte abyte0[], int j) {
		try {
			if (abyte0 == null) {
				ModelHeader class21 = aClass21Array1661[j] = new ModelHeader();
				class21.anInt369 = 0;
				class21.anInt370 = 0;
				class21.anInt371 = 0;
				return;
			}
			// dumpModels(j);
			ByteBuffer stream = new ByteBuffer(abyte0);
			stream.position = abyte0.length - 18;
			ModelHeader class21_1 = aClass21Array1661[j] = new ModelHeader();
			class21_1.aByteArray368 = abyte0;
			class21_1.anInt369 = stream.getUnsignedShort();
			class21_1.anInt370 = stream.getUnsignedShort();
			class21_1.anInt371 = stream.getUnsignedByte();
			int k = stream.getUnsignedByte();
			int l = stream.getUnsignedByte();
			int i1 = stream.getUnsignedByte();
			int j1 = stream.getUnsignedByte();
			int k1 = stream.getUnsignedByte();
			int l1 = stream.getUnsignedShort();
			int i2 = stream.getUnsignedShort();
			int j2 = stream.getUnsignedShort();
			int k2 = stream.getUnsignedShort();
			int l2 = 0;
			class21_1.anInt372 = l2;
			l2 += class21_1.anInt369;
			class21_1.anInt378 = l2;
			l2 += class21_1.anInt370;
			class21_1.anInt381 = l2;
			if (l == 255)
				l2 += class21_1.anInt370;
			else
				class21_1.anInt381 = -l - 1;
			class21_1.anInt383 = l2;
			if (j1 == 1)
				l2 += class21_1.anInt370;
			else
				class21_1.anInt383 = -1;
			class21_1.anInt380 = l2;
			if (k == 1)
				l2 += class21_1.anInt370;
			else
				class21_1.anInt380 = -1;
			class21_1.anInt376 = l2;
			if (k1 == 1)
				l2 += class21_1.anInt369;
			else
				class21_1.anInt376 = -1;
			class21_1.anInt382 = l2;
			if (i1 == 1)
				l2 += class21_1.anInt370;
			else
				class21_1.anInt382 = -1;
			class21_1.anInt377 = l2;
			l2 += k2;
			class21_1.anInt379 = l2;
			l2 += class21_1.anInt370 * 2;
			class21_1.anInt384 = l2;
			l2 += class21_1.anInt371 * 6;
			class21_1.anInt373 = l2;
			l2 += l1;
			class21_1.anInt374 = l2;
			l2 += i2;
			class21_1.anInt375 = l2;
			l2 += j2;
		} catch (Exception _ex) {
		}
	}

	public static boolean newmodel[];

	public static void initialize(int i, CacheFileRequester onDemandFetcherParent) {
		aClass21Array1661 = new ModelHeader[100000];
		newmodel = new boolean[100000];
		aOnDemandFetcherParent_1662 = onDemandFetcherParent;
	}

	public static void method461(int j) {
		aClass21Array1661[j] = null;
	}

	public static Model fetchModel(int fileId) {
		if (aClass21Array1661 == null) {
			return null;
		}
		if (fileId == 0) {
			return null;
		}
		ModelHeader modelHeader = aClass21Array1661[fileId];
		if (modelHeader == null) {
			int cacheIndex = CacheFileRequest.MODEL;// default model index
			if (fileId >= 65535) {
				fileId -= 65535;
				cacheIndex = CacheFileRequest.MODEL_EXT;// model_extended index
			}
			aOnDemandFetcherParent_1662.pushRequest(cacheIndex, fileId);
			return null;
		} else {
			return new Model(fileId);
		}
	}

	public static boolean isModelLoaded(int fileId) {
		if (aClass21Array1661 == null) {
			return false;
		}
		ModelHeader modelHeader = aClass21Array1661[fileId];
		if (modelHeader == null) {
			int cacheIndex = CacheFileRequest.MODEL;// default model index
			if (fileId >= 65535) {
				fileId -= 65535;
				cacheIndex = CacheFileRequest.MODEL_EXT;// model_extended index
			}
			aOnDemandFetcherParent_1662.pushRequest(cacheIndex, fileId);
			return false;
		} else {
			return true;
		}
	}

	/*public static Model method462(int j) {
		if (aClass21Array1661 == null)
			return null;
		ModelHeader class21 = aClass21Array1661[j];
		if (class21 == null) {
			aOnDemandFetcherParent_1662.method548(j);
			return null;
		} else {
			return new Model(j);
		}
	}

	public static boolean method463(int i) {
		if (aClass21Array1661 == null)
			return false;

		ModelHeader class21 = aClass21Array1661[i];
		if (class21 == null) {
			aOnDemandFetcherParent_1662.method548(i);
			return false;
		} else {
			return true;
		}
	}*/

	private Model(boolean flag) {
		aBoolean1618 = true;
		aBoolean1659 = false;
		if (!flag)
			aBoolean1618 = !aBoolean1618;
	}

	public Model(int i, Model amodel[]) {
		aBoolean1618 = true;
		aBoolean1659 = false;
		anInt1620++;
		boolean flag = false;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		anInt1626 = 0;
		anInt1630 = 0;
		anInt1642 = 0;
		anInt1641 = -1;
		for (int k = 0; k < i; k++) {
			Model model = amodel[k];
			if (model != null) {
				anInt1626 += model.anInt1626;
				anInt1630 += model.anInt1630;
				anInt1642 += model.anInt1642;
				flag |= model.anIntArray1637 != null;
				if (model.anIntArray1638 != null) {
					flag1 = true;
				} else {
					if (anInt1641 == -1)
						anInt1641 = model.anInt1641;
					if (anInt1641 != model.anInt1641)
						flag1 = true;
				}
				flag2 |= model.anIntArray1639 != null;
				flag3 |= model.anIntArray1656 != null;
			}
		}
		modelParticles = new int[anInt1626];
		anIntArray1627 = new int[anInt1626];
		anIntArray1628 = new int[anInt1626];
		anIntArray1629 = new int[anInt1626];
		anIntArray1655 = new int[anInt1626];
		anIntArray1631 = new int[anInt1630];
		anIntArray1632 = new int[anInt1630];
		anIntArray1633 = new int[anInt1630];
		anIntArray1643 = new int[anInt1642];
		anIntArray1644 = new int[anInt1642];
		anIntArray1645 = new int[anInt1642];
		if (flag)
			anIntArray1637 = new int[anInt1630];
		if (flag1)
			anIntArray1638 = new int[anInt1630];
		if (flag2)
			anIntArray1639 = new int[anInt1630];
		if (flag3)
			anIntArray1656 = new int[anInt1630];
		anIntArray1640 = new int[anInt1630];
		anInt1626 = 0;
		anInt1630 = 0;
		anInt1642 = 0;
		int l = 0;
		for (int i1 = 0; i1 < i; i1++) {
			Model model_1 = amodel[i1];
			if (model_1 != null) {
				for (int j1 = 0; j1 < model_1.anInt1630; j1++) {
					if (flag)
						if (model_1.anIntArray1637 == null) {
							anIntArray1637[anInt1630] = 0;
						} else {
							int k1 = model_1.anIntArray1637[j1];
							if ((k1 & 2) == 2)
								k1 += l << 2;
							anIntArray1637[anInt1630] = k1;
						}
					if (flag1)
						if (model_1.anIntArray1638 == null)
							anIntArray1638[anInt1630] = model_1.anInt1641;
						else
							anIntArray1638[anInt1630] = model_1.anIntArray1638[j1];
					if (flag2)
						if (model_1.anIntArray1639 == null)
							anIntArray1639[anInt1630] = 0;
						else
							anIntArray1639[anInt1630] = model_1.anIntArray1639[j1];

					if (flag3 && model_1.anIntArray1656 != null)
						anIntArray1656[anInt1630] = model_1.anIntArray1656[j1];
					anIntArray1640[anInt1630] = model_1.anIntArray1640[j1];
					anIntArray1631[anInt1630] = method465(model_1, model_1.anIntArray1631[j1]);
					anIntArray1632[anInt1630] = method465(model_1, model_1.anIntArray1632[j1]);
					anIntArray1633[anInt1630] = method465(model_1, model_1.anIntArray1633[j1]);
					anInt1630++;
				}

				for (int l1 = 0; l1 < model_1.anInt1642; l1++) {
					anIntArray1643[anInt1642] = method465(model_1, model_1.anIntArray1643[l1]);
					anIntArray1644[anInt1642] = method465(model_1, model_1.anIntArray1644[l1]);
					anIntArray1645[anInt1642] = method465(model_1, model_1.anIntArray1645[l1]);
					anInt1642++;
				}

				l += model_1.anInt1642;
			}
		}

	}

	public Model(Model amodel[]) {
		int i = 2;
		aBoolean1618 = true;
		aBoolean1659 = false;
		anInt1620++;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		anInt1626 = 0;
		anInt1630 = 0;
		anInt1642 = 0;
		anInt1641 = -1;
		for (int k = 0; k < i; k++) {
			Model model = amodel[k];
			if (model != null) {
				anInt1626 += model.anInt1626;
				anInt1630 += model.anInt1630;
				anInt1642 += model.anInt1642;
				flag1 |= model.anIntArray1637 != null;
				if (model.anIntArray1638 != null) {
					flag2 = true;
				} else {
					if (anInt1641 == -1)
						anInt1641 = model.anInt1641;
					if (anInt1641 != model.anInt1641)
						flag2 = true;
				}
				flag3 |= model.anIntArray1639 != null;
				flag4 |= model.anIntArray1640 != null;
			}
		}
		modelParticles = new int[anInt1626];
		anIntArray1627 = new int[anInt1626];
		anIntArray1628 = new int[anInt1626];
		anIntArray1629 = new int[anInt1626];
		anIntArray1631 = new int[anInt1630];
		anIntArray1632 = new int[anInt1630];
		anIntArray1633 = new int[anInt1630];
		anIntArray1634 = new int[anInt1630];
		anIntArray1635 = new int[anInt1630];
		anIntArray1636 = new int[anInt1630];
		anIntArray1643 = new int[anInt1642];
		anIntArray1644 = new int[anInt1642];
		anIntArray1645 = new int[anInt1642];
		if (flag1)
			anIntArray1637 = new int[anInt1630];
		if (flag2)
			anIntArray1638 = new int[anInt1630];
		if (flag3)
			anIntArray1639 = new int[anInt1630];
		if (flag4)
			anIntArray1640 = new int[anInt1630];
		anInt1626 = 0;
		anInt1630 = 0;
		anInt1642 = 0;
		int i1 = 0;
		for (int j1 = 0; j1 < i; j1++) {
			Model model_1 = amodel[j1];
			if (model_1 != null) {
				int k1 = anInt1626;
				for (int l1 = 0; l1 < model_1.anInt1626; l1++) {
					anIntArray1627[anInt1626] = model_1.anIntArray1627[l1];
					anIntArray1628[anInt1626] = model_1.anIntArray1628[l1];
					anIntArray1629[anInt1626] = model_1.anIntArray1629[l1];
					anInt1626++;
				}

				for (int i2 = 0; i2 < model_1.anInt1630; i2++) {
					anIntArray1631[anInt1630] = model_1.anIntArray1631[i2] + k1;
					anIntArray1632[anInt1630] = model_1.anIntArray1632[i2] + k1;
					anIntArray1633[anInt1630] = model_1.anIntArray1633[i2] + k1;
					anIntArray1634[anInt1630] = model_1.anIntArray1634[i2];
					anIntArray1635[anInt1630] = model_1.anIntArray1635[i2];
					anIntArray1636[anInt1630] = model_1.anIntArray1636[i2];
					if (flag1)
						if (model_1.anIntArray1637 == null) {
							anIntArray1637[anInt1630] = 0;
						} else {
							int j2 = model_1.anIntArray1637[i2];
							if ((j2 & 2) == 2)
								j2 += i1 << 2;
							anIntArray1637[anInt1630] = j2;
						}
					if (flag2)
						if (model_1.anIntArray1638 == null)
							anIntArray1638[anInt1630] = model_1.anInt1641;
						else
							anIntArray1638[anInt1630] = model_1.anIntArray1638[i2];
					if (flag3)
						if (model_1.anIntArray1639 == null)
							anIntArray1639[anInt1630] = 0;
						else
							anIntArray1639[anInt1630] = model_1.anIntArray1639[i2];
					if (flag4 && model_1.anIntArray1640 != null)
						anIntArray1640[anInt1630] = model_1.anIntArray1640[i2];

					anInt1630++;
				}

				for (int k2 = 0; k2 < model_1.anInt1642; k2++) {
					anIntArray1643[anInt1642] = model_1.anIntArray1643[k2] + k1;
					anIntArray1644[anInt1642] = model_1.anIntArray1644[k2] + k1;
					anIntArray1645[anInt1642] = model_1.anIntArray1645[k2] + k1;
					anInt1642++;
				}

				i1 += model_1.anInt1642;
			}
		}

		method466();
	}

	public Model(boolean flag, boolean flag1, boolean flag2, Model model) {
		aBoolean1618 = true;
		aBoolean1659 = false;
		anInt1620++;
		anInt1626 = model.anInt1626;
		anInt1630 = model.anInt1630;
		anInt1642 = model.anInt1642;
		if (flag2) {
			modelParticles = model.modelParticles;
			anIntArray1627 = model.anIntArray1627;
			anIntArray1628 = model.anIntArray1628;
			anIntArray1629 = model.anIntArray1629;
		} else {
			modelParticles = new int[anInt1626];
			anIntArray1627 = new int[anInt1626];
			anIntArray1628 = new int[anInt1626];
			anIntArray1629 = new int[anInt1626];
			for (int j = 0; j < anInt1626; j++) {
				modelParticles[j] = model.modelParticles[j];
				anIntArray1627[j] = model.anIntArray1627[j];
				anIntArray1628[j] = model.anIntArray1628[j];
				anIntArray1629[j] = model.anIntArray1629[j];
			}

		}
		if (flag) {
			anIntArray1640 = model.anIntArray1640;
		} else {
			anIntArray1640 = new int[anInt1630];
			for (int k = 0; k < anInt1630; k++)
				anIntArray1640[k] = model.anIntArray1640[k];

		}
		if (flag1) {
			anIntArray1639 = model.anIntArray1639;
		} else {
			anIntArray1639 = new int[anInt1630];
			if (model.anIntArray1639 == null) {
				for (int l = 0; l < anInt1630; l++)
					anIntArray1639[l] = 0;

			} else {
				for (int i1 = 0; i1 < anInt1630; i1++)
					anIntArray1639[i1] = model.anIntArray1639[i1];

			}
		}
		anIntArray1655 = model.anIntArray1655;
		anIntArray1656 = model.anIntArray1656;
		anIntArray1637 = model.anIntArray1637;
		anIntArray1631 = model.anIntArray1631;
		anIntArray1632 = model.anIntArray1632;
		anIntArray1633 = model.anIntArray1633;
		anIntArray1638 = model.anIntArray1638;
		anInt1641 = model.anInt1641;
		anIntArray1643 = model.anIntArray1643;
		anIntArray1644 = model.anIntArray1644;
		anIntArray1645 = model.anIntArray1645;
	}

	public Model(boolean flag, boolean flag1, Model model) {
		aBoolean1618 = true;
		aBoolean1659 = false;
		anInt1620++;
		anInt1626 = model.anInt1626;
		anInt1630 = model.anInt1630;
		anInt1642 = model.anInt1642;
		if (flag) {
			anIntArray1628 = new int[anInt1626];
			for (int j = 0; j < anInt1626; j++)
				anIntArray1628[j] = model.anIntArray1628[j];

		} else {
			anIntArray1628 = model.anIntArray1628;
		}
		if (flag1) {
			anIntArray1634 = new int[anInt1630];
			anIntArray1635 = new int[anInt1630];
			anIntArray1636 = new int[anInt1630];
			for (int k = 0; k < anInt1630; k++) {
				anIntArray1634[k] = model.anIntArray1634[k];
				anIntArray1635[k] = model.anIntArray1635[k];
				anIntArray1636[k] = model.anIntArray1636[k];
			}

			anIntArray1637 = new int[anInt1630];
			if (model.anIntArray1637 == null) {
				for (int l = 0; l < anInt1630; l++)
					anIntArray1637[l] = 0;

			} else {
				for (int i1 = 0; i1 < anInt1630; i1++)
					anIntArray1637[i1] = model.anIntArray1637[i1];

			}
			super.aClass33Array1425 = new Class33[anInt1626];
			for (int j1 = 0; j1 < anInt1626; j1++) {
				Class33 class33 = super.aClass33Array1425[j1] = new Class33();
				Class33 class33_1 = model.aClass33Array1425[j1];
				class33.anInt602 = class33_1.anInt602;
				class33.anInt603 = class33_1.anInt603;
				class33.anInt604 = class33_1.anInt604;
				class33.anInt605 = class33_1.anInt605;
			}

			aClass33Array1660 = model.aClass33Array1660;
		} else {
			anIntArray1634 = model.anIntArray1634;
			anIntArray1635 = model.anIntArray1635;
			anIntArray1636 = model.anIntArray1636;
			anIntArray1637 = model.anIntArray1637;
		}
		modelParticles = model.modelParticles;
		anIntArray1627 = model.anIntArray1627;
		anIntArray1629 = model.anIntArray1629;
		anIntArray1640 = model.anIntArray1640;
		anIntArray1639 = model.anIntArray1639;
		anIntArray1638 = model.anIntArray1638;
		anInt1641 = model.anInt1641;
		anIntArray1631 = model.anIntArray1631;
		anIntArray1632 = model.anIntArray1632;
		anIntArray1633 = model.anIntArray1633;
		anIntArray1643 = model.anIntArray1643;
		anIntArray1644 = model.anIntArray1644;
		anIntArray1645 = model.anIntArray1645;
		super.modelHeight = model.modelHeight;

		anInt1650 = model.anInt1650;
		anInt1653 = model.anInt1653;
		anInt1652 = model.anInt1652;
		anInt1646 = model.anInt1646;
		anInt1648 = model.anInt1648;
		anInt1649 = model.anInt1649;
		anInt1647 = model.anInt1647;
	}

	public void method464(Model model, boolean flag) {
		anInt1626 = model.anInt1626;
		anInt1630 = model.anInt1630;
		anInt1642 = model.anInt1642;
		if (anIntArray1622.length < anInt1626) {
			anIntArray1622 = new int[anInt1626 + 10000];
			anIntArray1623 = new int[anInt1626 + 10000];
			anIntArray1624 = new int[anInt1626 + 10000];
		}
		modelParticles = new int[anInt1626];
		anIntArray1627 = anIntArray1622;
		anIntArray1628 = anIntArray1623;
		anIntArray1629 = anIntArray1624;
		for (int k = 0; k < anInt1626; k++) {
			anIntArray1627[k] = model.anIntArray1627[k];
			anIntArray1628[k] = model.anIntArray1628[k];
			anIntArray1629[k] = model.anIntArray1629[k];
			if (modelParticles != null) {
				modelParticles[k] = model.modelParticles[k];
			}
		}

		if (flag) {
			anIntArray1639 = model.anIntArray1639;
		} else {
			if (anIntArray1625.length < anInt1630)
				anIntArray1625 = new int[anInt1630 + 100];
			anIntArray1639 = anIntArray1625;
			if (model.anIntArray1639 == null) {
				for (int l = 0; l < anInt1630; l++)
					anIntArray1639[l] = 0;

			} else {
				for (int i1 = 0; i1 < anInt1630; i1++)
					anIntArray1639[i1] = model.anIntArray1639[i1];

			}
		}
		anIntArray1637 = model.anIntArray1637;
		anIntArray1640 = model.anIntArray1640;
		anIntArray1638 = model.anIntArray1638;
		anInt1641 = model.anInt1641;
		anIntArrayArray1658 = model.anIntArrayArray1658;
		anIntArrayArray1657 = model.anIntArrayArray1657;
		anIntArray1631 = model.anIntArray1631;
		anIntArray1632 = model.anIntArray1632;
		anIntArray1633 = model.anIntArray1633;
		anIntArray1634 = model.anIntArray1634;
		anIntArray1635 = model.anIntArray1635;
		anIntArray1636 = model.anIntArray1636;
		anIntArray1643 = model.anIntArray1643;
		anIntArray1644 = model.anIntArray1644;
		anIntArray1645 = model.anIntArray1645;
	}

	private final int method465(Model model, int i) {
		int j = -1;
		int var4 = model.modelParticles[i];
		int k = model.anIntArray1627[i];
		int l = model.anIntArray1628[i];
		int i1 = model.anIntArray1629[i];
		for (int j1 = 0; j1 < anInt1626; j1++) {
			if (k != anIntArray1627[j1] || l != anIntArray1628[j1] || i1 != anIntArray1629[j1])
				continue;
			j = j1;
			break;
		}

		if (j == -1) {
			modelParticles[anInt1626] = var4;
			anIntArray1627[anInt1626] = k;
			anIntArray1628[anInt1626] = l;
			anIntArray1629[anInt1626] = i1;
			if (model.anIntArray1655 != null)
				anIntArray1655[anInt1626] = model.anIntArray1655[i];
			j = anInt1626++;
		}
		return j;
	}

	public void method466() {
		super.modelHeight = 0;
		anInt1650 = 0;
		anInt1651 = 0;
		for (int i = 0; i < anInt1626; i++) {
			int j = anIntArray1627[i];
			int k = anIntArray1628[i];
			int l = anIntArray1629[i];
			if (-k > super.modelHeight)
				super.modelHeight = -k;
			if (k > anInt1651)
				anInt1651 = k;
			int i1 = j * j + l * l;
			if (i1 > anInt1650)
				anInt1650 = i1;
		}
		anInt1650 = (int) (Math.sqrt(anInt1650) + 0.98999999999999999D);
		anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight)
				+ 0.98999999999999999D);
		anInt1652 = anInt1653 + (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651) + 0.98999999999999999D);
	}

	public void method467() {
		super.modelHeight = 0;
		anInt1651 = 0;
		for (int i = 0; i < anInt1626; i++) {
			int j = anIntArray1628[i];
			if (-j > super.modelHeight)
				super.modelHeight = -j;
			if (j > anInt1651)
				anInt1651 = j;
		}

		anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight)
				+ 0.98999999999999999D);
		anInt1652 = anInt1653 + (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651) + 0.98999999999999999D);
	}

	public void method468(int i) {
		super.modelHeight = 0;
		anInt1650 = 0;
		anInt1651 = 0;
		anInt1646 = 0xf423f;
		anInt1647 = 0xfff0bdc1;
		anInt1648 = 0xfffe7961;
		anInt1649 = 0x1869f;
		for (int j = 0; j < anInt1626; j++) {
			int k = anIntArray1627[j];
			int l = anIntArray1628[j];
			int i1 = anIntArray1629[j];
			if (k < anInt1646)
				anInt1646 = k;
			if (k > anInt1647)
				anInt1647 = k;
			if (i1 < anInt1649)
				anInt1649 = i1;
			if (i1 > anInt1648)
				anInt1648 = i1;
			if (-l > super.modelHeight)
				super.modelHeight = -l;
			if (l > anInt1651)
				anInt1651 = l;
			int j1 = k * k + i1 * i1;
			if (j1 > anInt1650)
				anInt1650 = j1;
		}

		anInt1650 = (int) Math.sqrt(anInt1650);
		anInt1653 = (int) Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight);
		if (i != 21073) {
			return;
		} else {
			anInt1652 = anInt1653 + (int) Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651);
			return;
		}
	}

	public void method469() {
		if (anIntArray1655 != null) {
			int ai[] = new int[256];
			int j = 0;
			for (int l = 0; l < anInt1626; l++) {
				int j1 = anIntArray1655[l];
				ai[j1]++;
				if (j1 > j)
					j = j1;
			}

			anIntArrayArray1657 = new int[j + 1][];
			for (int k1 = 0; k1 <= j; k1++) {
				anIntArrayArray1657[k1] = new int[ai[k1]];
				ai[k1] = 0;
			}

			for (int j2 = 0; j2 < anInt1626; j2++) {
				int l2 = anIntArray1655[j2];
				anIntArrayArray1657[l2][ai[l2]++] = j2;
			}

			anIntArray1655 = null;
		}
		if (anIntArray1656 != null) {
			int ai1[] = new int[256];
			int k = 0;
			for (int i1 = 0; i1 < anInt1630; i1++) {
				int l1 = anIntArray1656[i1];
				ai1[l1]++;
				if (l1 > k)
					k = l1;
			}

			anIntArrayArray1658 = new int[k + 1][];
			for (int i2 = 0; i2 <= k; i2++) {
				anIntArrayArray1658[i2] = new int[ai1[i2]];
				ai1[i2] = 0;
			}

			for (int k2 = 0; k2 < anInt1630; k2++) {
				int i3 = anIntArray1656[k2];
				anIntArrayArray1658[i3][ai1[i3]++] = k2;
			}

			anIntArray1656 = null;
		}
	}

	public void method470(int i) {
		if (anIntArrayArray1657 == null)
			return;
		if (i == -1)
			return;
		FrameReader class36 = FrameReader.forId(i);
		if (class36 == null)
			return;
		SkinList class18 = class36.skinList;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		for (int k = 0; k < class36.stepCount; k++) {
			int l = class36.opcodeLinkTable[k];
			method472(class18.opcodes[l], class18.skinList[l], class36.xOffset[k], class36.yOffset[k],
					class36.zOffset[k]);
		}

	}

	public void method471(int ai[], int j, int k) {
		if (k == -1) {
			return;
		}
		if (ai == null || j == -1) {
			applyTransform(k);
			return;
		}
		FrameReader class36 = FrameReader.forId(k);
		if (class36 == null) {
			return;
		}
		FrameReader class36_1 = FrameReader.forId(j);
		if (class36_1 == null) {
			applyTransform(k);
			return;
		}
		SkinList class18 = class36.skinList;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		int l = 0;
		int i1 = ai[l++];
		for (int j1 = 0; j1 < class36.stepCount; j1++) {
			int k1;
			for (k1 = class36.opcodeLinkTable[j1]; k1 > i1; i1 = ai[l++]) {
				;
			}
			if (k1 != i1 || class18.opcodes[k1] == 0) {
				method472(class18.opcodes[k1], class18.skinList[k1], class36.xOffset[j1], class36.yOffset[j1],
						class36.zOffset[j1]);
			}
		}

		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		l = 0;
		i1 = ai[l++];
		for (int l1 = 0; l1 < class36_1.stepCount; l1++) {
			int i2;
			for (i2 = class36_1.opcodeLinkTable[l1]; i2 > i1; i1 = ai[l++]) {
				;
			}
			if (i2 == i1 || class18.opcodes[i2] == 0) {
				method472(class18.opcodes[i2], class18.skinList[i2], class36_1.xOffset[l1], class36_1.yOffset[l1],
						class36_1.zOffset[l1]);
			}
		}

	}

	private void method472(int i, int ai[], int j, int k, int l) {

		int i1 = ai.length;
		if (i == 0) {
			int j1 = 0;
			anInt1681 = 0;
			anInt1682 = 0;
			anInt1683 = 0;
			for (int k2 = 0; k2 < i1; k2++) {
				int l3 = ai[k2];
				if (l3 < anIntArrayArray1657.length) {
					int ai5[] = anIntArrayArray1657[l3];
					for (int i5 = 0; i5 < ai5.length; i5++) {
						int j6 = ai5[i5];
						anInt1681 += anIntArray1627[j6];
						anInt1682 += anIntArray1628[j6];
						anInt1683 += anIntArray1629[j6];
						j1++;
					}

				}
			}

			if (j1 > 0) {
				anInt1681 = anInt1681 / j1 + j;
				anInt1682 = anInt1682 / j1 + k;
				anInt1683 = anInt1683 / j1 + l;
				return;
			} else {
				anInt1681 = j;
				anInt1682 = k;
				anInt1683 = l;
				return;
			}
		}
		if (i == 1) {
			for (int k1 = 0; k1 < i1; k1++) {
				int l2 = ai[k1];
				if (l2 < anIntArrayArray1657.length) {
					int ai1[] = anIntArrayArray1657[l2];
					for (int i4 = 0; i4 < ai1.length; i4++) {
						int j5 = ai1[i4];
						anIntArray1627[j5] += j;
						anIntArray1628[j5] += k;
						anIntArray1629[j5] += l;
					}

				}
			}

			return;
		}
		if (i == 2) {
			for (int l1 = 0; l1 < i1; l1++) {
				int i3 = ai[l1];
				if (i3 < anIntArrayArray1657.length) {
					int ai2[] = anIntArrayArray1657[i3];
					for (int j4 = 0; j4 < ai2.length; j4++) {
						int k5 = ai2[j4];
						anIntArray1627[k5] -= anInt1681;
						anIntArray1628[k5] -= anInt1682;
						anIntArray1629[k5] -= anInt1683;
						int k6 = (j & 0xff) * 8;
						int l6 = (k & 0xff) * 8;
						int i7 = (l & 0xff) * 8;
						if (i7 != 0) {
							int j7 = modelIntArray1[i7];
							int i8 = modelIntArray2[i7];
							int l8 = anIntArray1628[k5] * j7 + anIntArray1627[k5] * i8 >> 16;
							anIntArray1628[k5] = anIntArray1628[k5] * i8 - anIntArray1627[k5] * j7 >> 16;
							anIntArray1627[k5] = l8;
						}
						if (k6 != 0) {
							int k7 = modelIntArray1[k6];
							int j8 = modelIntArray2[k6];
							int i9 = anIntArray1628[k5] * j8 - anIntArray1629[k5] * k7 >> 16;
							anIntArray1629[k5] = anIntArray1628[k5] * k7 + anIntArray1629[k5] * j8 >> 16;
							anIntArray1628[k5] = i9;
						}
						if (l6 != 0) {
							int l7 = modelIntArray1[l6];
							int k8 = modelIntArray2[l6];
							int j9 = anIntArray1629[k5] * l7 + anIntArray1627[k5] * k8 >> 16;
							anIntArray1629[k5] = anIntArray1629[k5] * k8 - anIntArray1627[k5] * l7 >> 16;
							anIntArray1627[k5] = j9;
						}
						anIntArray1627[k5] += anInt1681;
						anIntArray1628[k5] += anInt1682;
						anIntArray1629[k5] += anInt1683;
					}

				}
			}
			return;
		}
		if (i == 3) {
			for (int i2 = 0; i2 < i1; i2++) {
				int j3 = ai[i2];
				if (j3 < anIntArrayArray1657.length) {
					int ai3[] = anIntArrayArray1657[j3];
					for (int k4 = 0; k4 < ai3.length; k4++) {
						int l5 = ai3[k4];
						anIntArray1627[l5] -= anInt1681;
						anIntArray1628[l5] -= anInt1682;
						anIntArray1629[l5] -= anInt1683;
						anIntArray1627[l5] = (anIntArray1627[l5] * j) / 128;
						anIntArray1628[l5] = (anIntArray1628[l5] * k) / 128;
						anIntArray1629[l5] = (anIntArray1629[l5] * l) / 128;
						anIntArray1627[l5] += anInt1681;
						anIntArray1628[l5] += anInt1682;
						anIntArray1629[l5] += anInt1683;
					}
				}
			}
			return;
		}
		if (i == 5 && anIntArrayArray1658 != null && anIntArray1639 != null) {
			for (int j2 = 0; j2 < i1; j2++) {
				int k3 = ai[j2];
				if (k3 < anIntArrayArray1658.length) {
					int ai4[] = anIntArrayArray1658[k3];
					for (int l4 = 0; l4 < ai4.length; l4++) {
						int i6 = ai4[l4];
						anIntArray1639[i6] += j * 8;
						if (anIntArray1639[i6] < 0)
							anIntArray1639[i6] = 0;
						if (anIntArray1639[i6] > 255)
							anIntArray1639[i6] = 255;
					}
				}
			}
		}
	}

	public void method473() {
		for (int j = 0; j < anInt1626; j++) {
			int k = anIntArray1627[j];
			anIntArray1627[j] = anIntArray1629[j];
			anIntArray1629[j] = -k;
		}
	}

	public void method474(int i) {
		int k = modelIntArray1[i];
		int l = modelIntArray2[i];
		for (int i1 = 0; i1 < anInt1626; i1++) {
			int j1 = anIntArray1628[i1] * l - anIntArray1629[i1] * k >> 16;
			anIntArray1629[i1] = anIntArray1628[i1] * k + anIntArray1629[i1] * l >> 16;
			anIntArray1628[i1] = j1;
		}
	}

	public void method475(int i, int j, int l) {
		for (int i1 = 0; i1 < anInt1626; i1++) {
			anIntArray1627[i1] += i;
			anIntArray1628[i1] += j;
			anIntArray1629[i1] += l;
		}
	}

	public void method476(int i, int j) {
		for (int k = 0; k < anInt1630; k++)
			if (anIntArray1640[k] == i)
				anIntArray1640[k] = j;
	}

	public void method477() {
		for (int j = 0; j < anInt1626; j++)
			anIntArray1629[j] = -anIntArray1629[j];
		for (int k = 0; k < anInt1630; k++) {
			int l = anIntArray1631[k];
			anIntArray1631[k] = anIntArray1633[k];
			anIntArray1633[k] = l;
		}
	}

	public void method478(int i, int j, int l) {
		for (int i1 = 0; i1 < anInt1626; i1++) {
			anIntArray1627[i1] = (anIntArray1627[i1] * i) / 128;
			anIntArray1628[i1] = (anIntArray1628[i1] * l) / 128;
			anIntArray1629[i1] = (anIntArray1629[i1] * j) / 128;
		}

	}

	public final void method479(int i, int j, int k, int l, int i1, boolean flag) {
		int j1 = (int) Math.sqrt(k * k + l * l + i1 * i1);
		int k1 = j * j1 >> 8;
		if (anIntArray1634 == null) {
			anIntArray1634 = new int[anInt1630];
			anIntArray1635 = new int[anInt1630];
			anIntArray1636 = new int[anInt1630];
		}
		removeColors(new int[] { 37798 });
		if (super.aClass33Array1425 == null) {
			super.aClass33Array1425 = new Class33[anInt1626];
			for (int l1 = 0; l1 < anInt1626; l1++)
				super.aClass33Array1425[l1] = new Class33();

		}
		for (int i2 = 0; i2 < anInt1630; i2++) {
			if (anIntArray1640 != null && anIntArray1639 != null)
				if (anIntArray1640[i2] == 65535 || anIntArray1640[i2] == 16705)
					anIntArray1639[i2] = 255;
			int j2 = anIntArray1631[i2];
			int l2 = anIntArray1632[i2];
			int i3 = anIntArray1633[i2];
			int j3 = anIntArray1627[l2] - anIntArray1627[j2];
			int k3 = anIntArray1628[l2] - anIntArray1628[j2];
			int l3 = anIntArray1629[l2] - anIntArray1629[j2];
			int i4 = anIntArray1627[i3] - anIntArray1627[j2];
			int j4 = anIntArray1628[i3] - anIntArray1628[j2];
			int k4 = anIntArray1629[i3] - anIntArray1629[j2];
			int l4 = k3 * k4 - j4 * l3;
			int i5 = l3 * i4 - k4 * j3;
			int j5;
			for (j5 = j3 * j4 - i4 * k3; l4 > 8192 || i5 > 8192 || j5 > 8192 || l4 < -8192 || i5 < -8192
					|| j5 < -8192; j5 >>= 1) {
				l4 >>= 1;
				i5 >>= 1;
			}

			int k5 = (int) Math.sqrt(l4 * l4 + i5 * i5 + j5 * j5);
			if (k5 <= 0)
				k5 = 1;
			l4 = (l4 * 256) / k5;
			i5 = (i5 * 256) / k5;
			j5 = (j5 * 256) / k5;

			if (anIntArray1637 == null || (anIntArray1637[i2] & 1) == 0) {

				Class33 class33_2 = super.aClass33Array1425[j2];
				class33_2.anInt602 += l4;
				class33_2.anInt603 += i5;
				class33_2.anInt604 += j5;
				class33_2.anInt605++;
				class33_2 = super.aClass33Array1425[l2];
				class33_2.anInt602 += l4;
				class33_2.anInt603 += i5;
				class33_2.anInt604 += j5;
				class33_2.anInt605++;
				class33_2 = super.aClass33Array1425[i3];
				class33_2.anInt602 += l4;
				class33_2.anInt603 += i5;
				class33_2.anInt604 += j5;
				class33_2.anInt605++;

			} else {

				int l5 = i + (k * l4 + l * i5 + i1 * j5) / (k1 + k1 / 2);
				anIntArray1634[i2] = method481(anIntArray1640[i2], l5, anIntArray1637[i2]);

			}
		}

		if (flag) {
			method480(i, k1, k, l, i1);
		} else {
			aClass33Array1660 = new Class33[anInt1626];
			for (int k2 = 0; k2 < anInt1626; k2++) {
				Class33 class33 = super.aClass33Array1425[k2];
				Class33 class33_1 = aClass33Array1660[k2] = new Class33();
				class33_1.anInt602 = class33.anInt602;
				class33_1.anInt603 = class33.anInt603;
				class33_1.anInt604 = class33.anInt604;
				class33_1.anInt605 = class33.anInt605;
			}

		}
		if (flag) {
			method466();
			return;
		} else {
			method468(21073);
			return;
		}
	}

	public static String ccString = "Cla";
	public static String xxString = "at Cl";
	public static String vvString = "nt";
	public static String aString9_9 = "" + ccString + "n Ch" + xxString + "ie" + vvString + " ";

	public final void method480(int i, int j, int k, int l, int i1) {
		for (int j1 = 0; j1 < anInt1630; j1++) {
			int k1 = anIntArray1631[j1];
			int i2 = anIntArray1632[j1];
			int j2 = anIntArray1633[j1];
			if (anIntArray1637 == null) {
				int i3 = anIntArray1640[j1];
				Class33 class33 = super.aClass33Array1425[k1];
				int k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604)
						/ (j * class33.anInt605);
				anIntArray1634[j1] = method481(i3, k2, 0);
				class33 = super.aClass33Array1425[i2];
				k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
				anIntArray1635[j1] = method481(i3, k2, 0);
				class33 = super.aClass33Array1425[j2];
				k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
				anIntArray1636[j1] = method481(i3, k2, 0);
			} else if ((anIntArray1637[j1] & 1) == 0) {
				int j3 = anIntArray1640[j1];
				int k3 = anIntArray1637[j1];
				Class33 class33_1 = super.aClass33Array1425[k1];
				int l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604)
						/ (j * class33_1.anInt605);
				anIntArray1634[j1] = method481(j3, l2, k3);
				class33_1 = super.aClass33Array1425[i2];
				l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604)
						/ (j * class33_1.anInt605);
				anIntArray1635[j1] = method481(j3, l2, k3);
				class33_1 = super.aClass33Array1425[j2];
				l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604)
						/ (j * class33_1.anInt605);
				anIntArray1636[j1] = method481(j3, l2, k3);
			}
		}

		super.aClass33Array1425 = null;
		aClass33Array1660 = null;
		anIntArray1655 = null;
		anIntArray1656 = null;
		if (anIntArray1637 != null) {
			for (int l1 = 0; l1 < anInt1630; l1++)
				if ((anIntArray1637[l1] & 2) == 2)
					return;

		}
		anIntArray1640 = null;
	}

	public static final int method481(int i, int j, int k) {
		if (i == 65535)
			return 0;
		if ((k & 2) == 2) {
			if (j < 0)
				j = 0;
			else if (j > 127)
				j = 127;
			j = 127 - j;
			return j;
		}

		j = j * (i & 0x7f) >> 7;
		if (j < 2)
			j = 2;
		else if (j > 126)
			j = 126;
		return (i & 0xff80) + j;
	}

	public final void method482(int j, int k, int l, int i1, int j1, int k1) {
		int i = 0;
		int l1 = Canvas3D.centerX;
		int i2 = Canvas3D.centerY;
		int j2 = modelIntArray1[i];
		int k2 = modelIntArray2[i];
		int l2 = modelIntArray1[j];
		int i3 = modelIntArray2[j];
		int j3 = modelIntArray1[k];
		int k3 = modelIntArray2[k];
		int l3 = modelIntArray1[l];
		int i4 = modelIntArray2[l];
		int j4 = j1 * l3 + k1 * i4 >> 16;
		for (int k4 = 0; k4 < anInt1626; k4++) {
			int l4 = anIntArray1627[k4];
			int i5 = anIntArray1628[k4];
			int j5 = anIntArray1629[k4];
			if (k != 0) {
				int k5 = i5 * j3 + l4 * k3 >> 16;
				i5 = i5 * k3 - l4 * j3 >> 16;
				l4 = k5;
			}
			if (i != 0) {
				int l5 = i5 * k2 - j5 * j2 >> 16;
				j5 = i5 * j2 + j5 * k2 >> 16;
				i5 = l5;
			}
			if (j != 0) {
				int i6 = j5 * l2 + l4 * i3 >> 16;
				j5 = j5 * i3 - l4 * l2 >> 16;
				l4 = i6;
			}
			l4 += i1;
			i5 += j1;
			j5 += k1;
			int j6 = i5 * i4 - j5 * l3 >> 16;
			j5 = i5 * l3 + j5 * i4 >> 16;
			i5 = j6;
			anIntArray1667[k4] = j5 - j4;
			vertexPerspectiveDepth[k4] = j5;
			anIntArray1665[k4] = l1 + (l4 << 9) / j5;
			anIntArray1666[k4] = i2 + (i5 << 9) / j5;
			if (anInt1642 > 0) {
				anIntArray1668[k4] = l4;
				anIntArray1669[k4] = i5;
				anIntArray1670[k4] = j5;
			}
		}

		try {
			method483(false, false, 0, 0);
			return;
		} catch (Exception _ex) {
			return;
		}
	}

	public final void method443(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int newuid) {
		J = j1 + Client.instance.xCameraPos;
		S = k1 + Client.instance.zCameraPos;
		A = l1 + Client.instance.yCameraPos;
		E = i;
		int j2 = l1 * i1 - j1 * l >> 16;
		int k2 = k1 * j + j2 * k >> 16;
		int l2 = anInt1650 * k >> 16;
		int i3 = k2 + l2;
		if (i3 <= 50 || k2 >= 6500)
			return;
		int j3 = l1 * l + j1 * i1 >> 16;
		int k3 = j3 - anInt1650 << Client.log_view_dist;
		if (k3 / i3 >= Canvas2D.centerY)
			return;
		int l3 = j3 + anInt1650 << Client.log_view_dist;
		if (l3 / i3 <= -Canvas2D.centerY)
			return;
		int i4 = k1 * k - j2 * j >> 16;
		int j4 = anInt1650 * j >> 16;
		int k4 = i4 + j4 << Client.log_view_dist;
		if (k4 / i3 <= -Canvas2D.middleY)
			return;
		int l4 = j4 + (super.modelHeight * k >> 16);
		int i5 = i4 - l4 << Client.log_view_dist;
		if (i5 / i3 >= Canvas2D.middleY)
			return;
		int j5 = l2 + (super.modelHeight * j >> 16);
		boolean flag = false;
		if (k2 - j5 <= 50)
			flag = true;
		boolean flag1 = false;
		if (i2 > 0 && aBoolean1684) {
			int k5 = k2 - l2;
			if (k5 <= 50)
				k5 = 50;
			if (j3 > 0) {
				k3 /= i3;
				l3 /= k5;
			} else {
				l3 /= i3;
				k3 /= k5;
			}
			if (i4 > 0) {
				i5 /= i3;
				k4 /= k5;
			} else {
				k4 /= i3;
				i5 /= k5;
			}
			int i6 = anInt1685 - Canvas3D.centerX;
			int k6 = anInt1686 - Canvas3D.centerY;
			if (i6 > k3 && i6 < l3 && k6 > i5 && k6 < k4)
				if (aBoolean1659) {
					mapObjIds[anInt1687] = newuid;
					anIntArray1688[anInt1687++] = i2;
				} else {
					flag1 = true;
				}
		}
		int l5 = Canvas3D.centerX;
		int j6 = Canvas3D.centerY;
		int l6 = 0;
		int i7 = 0;
		if (i != 0) {
			l6 = modelIntArray1[i];
			i7 = modelIntArray2[i];
		}
		for (int j7 = 0; j7 < anInt1626; j7++) {
			int k7 = anIntArray1627[j7];
			int l7 = anIntArray1628[j7];
			int i8 = anIntArray1629[j7];
			if (i != 0) {
				int j8 = i8 * l6 + k7 * i7 >> 16;
				i8 = i8 * i7 - k7 * l6 >> 16;
				k7 = j8;
			}
			k7 += j1;
			l7 += k1;
			i8 += l1;
			int k8 = i8 * l + k7 * i1 >> 16;
			i8 = i8 * i1 - k7 * l >> 16;
			k7 = k8;
			k8 = l7 * k - i8 * j >> 16;
			i8 = l7 * j + i8 * k >> 16;
			l7 = k8;
			anIntArray1667[j7] = i8 - k2;
			vertexPerspectiveDepth[j7] = i8;
			if (i8 >= 50) {
				anIntArray1665[j7] = l5 + (k7 << Client.log_view_dist) / i8;
				anIntArray1666[j7] = j6 + (l7 << Client.log_view_dist) / i8;
			} else {
				anIntArray1665[j7] = -5000;
				flag = true;
			}
			if (flag || anInt1642 > 0) {
				anIntArray1668[j7] = k7;
				anIntArray1669[j7] = l7;
				anIntArray1670[j7] = i8;
			}
		}

		try {
			method483(flag, flag1, i2, newuid);
			return;
		} catch (Exception _ex) {
			return;
		}
	}

	private final void method483(boolean flag, boolean flag1, int i, int id) {
		for (int j = 0; j < anInt1652; j++)
			anIntArray1671[j] = 0;

		for (int k = 0; k < anInt1630; k++)
			if (anIntArray1637 == null || anIntArray1637[k] != -1) {
				int l = anIntArray1631[k];
				int k1 = anIntArray1632[k];
				int j2 = anIntArray1633[k];
				int i3 = anIntArray1665[l];
				int l3 = anIntArray1665[k1];
				int k4 = anIntArray1665[j2];
				if (flag && (i3 == -5000 || l3 == -5000 || k4 == -5000)) {
					aBooleanArray1664[k] = true;
					int j5 = (anIntArray1667[l] + anIntArray1667[k1] + anIntArray1667[j2]) / 3 + anInt1653;
					anIntArrayArray1672[j5][anIntArray1671[j5]++] = k;
				} else {
					if (flag1 && method486(anInt1685, anInt1686, anIntArray1666[l], anIntArray1666[k1],
							anIntArray1666[j2], i3, l3, k4)) {
						mapObjIds[anInt1687] = id;
						anIntArray1688[anInt1687++] = i;
						flag1 = false;
					}
					if ((i3 - l3) * (anIntArray1666[j2] - anIntArray1666[k1])
							- (anIntArray1666[l] - anIntArray1666[k1]) * (k4 - l3) > 0) {
						aBooleanArray1664[k] = false;
						if (i3 < 0 || l3 < 0 || k4 < 0 || i3 > Canvas2D.centerX || l3 > Canvas2D.centerX
								|| k4 > Canvas2D.centerX)
							aBooleanArray1663[k] = true;
						else
							aBooleanArray1663[k] = false;
						int k5 = (anIntArray1667[l] + anIntArray1667[k1] + anIntArray1667[j2]) / 3 + anInt1653;
						anIntArrayArray1672[k5][anIntArray1671[k5]++] = k;
					}
				}
			}

		if (anIntArray1638 == null) {
			for (int i1 = anInt1652 - 1; i1 >= 0; i1--) {
				int l1 = anIntArray1671[i1];
				if (l1 > 0) {
					int ai[] = anIntArrayArray1672[i1];
					for (int j3 = 0; j3 < l1; j3++)
						method484(ai[j3]);

				}
			}

			return;
		}
		for (int j1 = 0; j1 < 12; j1++) {
			anIntArray1673[j1] = 0;
			anIntArray1677[j1] = 0;
		}

		for (int i2 = anInt1652 - 1; i2 >= 0; i2--) {
			int k2 = anIntArray1671[i2];
			if (k2 > 0) {
				int ai1[] = anIntArrayArray1672[i2];
				for (int i4 = 0; i4 < k2; i4++) {
					int l4 = ai1[i4];
					int l5 = anIntArray1638[l4];
					int j6 = anIntArray1673[l5]++;
					anIntArrayArray1674[l5][j6] = l4;
					if (l5 < 10)
						anIntArray1677[l5] += i2;
					else if (l5 == 10)
						anIntArray1675[j6] = i2;
					else
						anIntArray1676[j6] = i2;
				}

			}
		}

		int l2 = 0;
		if (anIntArray1673[1] > 0 || anIntArray1673[2] > 0)
			l2 = (anIntArray1677[1] + anIntArray1677[2]) / (anIntArray1673[1] + anIntArray1673[2]);
		int k3 = 0;
		if (anIntArray1673[3] > 0 || anIntArray1673[4] > 0)
			k3 = (anIntArray1677[3] + anIntArray1677[4]) / (anIntArray1673[3] + anIntArray1673[4]);
		int j4 = 0;
		if (anIntArray1673[6] > 0 || anIntArray1673[8] > 0)
			j4 = (anIntArray1677[6] + anIntArray1677[8]) / (anIntArray1673[6] + anIntArray1673[8]);
		int i6 = 0;
		int k6 = anIntArray1673[10];
		int ai2[] = anIntArrayArray1674[10];
		int ai3[] = anIntArray1675;
		if (i6 == k6) {
			i6 = 0;
			k6 = anIntArray1673[11];
			ai2 = anIntArrayArray1674[11];
			ai3 = anIntArray1676;
		}
		int i5;
		if (i6 < k6)
			i5 = ai3[i6];
		else
			i5 = -1000;
		for (int l6 = 0; l6 < 10; l6++) {
			while (l6 == 0 && i5 > l2) {
				method484(ai2[i6++]);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6)
					i5 = ai3[i6];
				else
					i5 = -1000;
			}
			while (l6 == 3 && i5 > k3) {
				method484(ai2[i6++]);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6)
					i5 = ai3[i6];
				else
					i5 = -1000;
			}
			while (l6 == 5 && i5 > j4) {
				method484(ai2[i6++]);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6)
					i5 = ai3[i6];
				else
					i5 = -1000;
			}
			int i7 = anIntArray1673[l6];
			int ai4[] = anIntArrayArray1674[l6];
			for (int j7 = 0; j7 < i7; j7++)
				method484(ai4[j7]);

		}

		while (i5 != -1000) {
			method484(ai2[i6++]);
			if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
				i6 = 0;
				ai2 = anIntArrayArray1674[11];
				k6 = anIntArray1673[11];
				ai3 = anIntArray1676;
			}
			if (i6 < k6)
				i5 = ai3[i6];
			else
				i5 = -1000;
		}
		for (int vertex = 0; vertex < anInt1626; ++vertex) {
			int var26 = modelParticles[vertex] - 1;
			if (var26 >= 0) {
				Particle var27 = Particle.PARTICLE_ARRAY[var26];
				int x = anIntArray1627[vertex];
				int y = anIntArray1628[vertex];
				int z = anIntArray1629[vertex];
				int depth = vertexPerspectiveDepth[vertex];
				int var21;
				if (E != 0) {
					int var20 = modelIntArray1[E];
					var21 = modelIntArray2[E];
					int var22 = z * var20 + x * var21 >> 16;
					z = z * var21 - x * var20 >> 16;
					x = var22;
				}

				x += J;
				z += A;
				Position point = new Position(x, -y, z);

				for (var21 = 0; var21 < var27.D(); ++var21) {
					boolean rainbowColor = false;
					ParticleDisplay display = new ParticleDisplay(var27, point, depth);
					if (rainbowColor) {
						int mspercolor = 1000;
						int ms = (int)(System.currentTimeMillis() % (mspercolor * rainbow.length));
						int currentcolor = ms / mspercolor;
						int timepassed = ms % mspercolor;
						int[] colora = rainbow[currentcolor % rainbow.length];
						int[] colorb = rainbow[(currentcolor+1) % rainbow.length];
						int[] colorc = new int[] {
							colora[0] + ((colorb[0] - colora[0]) * timepassed / mspercolor),
							colora[1] + ((colorb[1] - colora[1]) * timepassed / mspercolor),
							colora[2] + ((colorb[2] - colora[2]) * timepassed / mspercolor),
						};
						display.setRgb(getRGBInt(colorc[0], colorc[1], colorc[2]));
					}
					Client.instance.I(display);
				}
			}
		}
	}

	private static int[][] rainbow = new int[][] {
		{255, 0, 0}, // red
		{255, 200, 0 }, // orange
		{255, 255, 0}, // yellow
		{0, 255, 0}, // green
		{0, 0, 255}, // blue
		{255, 0, 255}, // purple
	};

	public static int getRGBInt(int r, int g, int b) {
		int color = 0;
		color |= ((r & 0xff) << 16);
		color |= ((g & 0xff) << 8);
		color |= ((b & 0xff));
		return color;
	}

	private int E = 0;
	private int J;
	public int S = 0;
	public int A = 0;
	public int[] modelParticles;

	// TODO
	private final void method484(int i) {
		if (aBooleanArray1664[i]) {
			method485(i);
			return;
		}
		int j = anIntArray1631[i];
		int k = anIntArray1632[i];
		int l = anIntArray1633[i];
		Canvas3D.restrict_edges = aBooleanArray1663[i];
		if (anIntArray1639 == null)
			Canvas3D.alpha = 0;
		else
			Canvas3D.alpha = anIntArray1639[i];
		int i1;
		if (anIntArray1637 == null)
			i1 = 0;
		else
			i1 = anIntArray1637[i] & 3;
		if (i1 == 0) {
			Canvas3D.method374(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j],
					anIntArray1665[k], anIntArray1665[l], anIntArray1634[i], anIntArray1635[i], anIntArray1636[i], vertexPerspectiveDepth[j], vertexPerspectiveDepth[k],
					vertexPerspectiveDepth[l]);
			return;
		}
		if (i1 == 1) {
			Canvas3D.method376(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j],
					anIntArray1665[k], anIntArray1665[l], modelIntArray3[anIntArray1634[i]], vertexPerspectiveDepth[j], vertexPerspectiveDepth[k],
					vertexPerspectiveDepth[l]);
			return;
		}
		if (i1 == 2) {
			int j1 = anIntArray1637[i] >> 2;
			int l1 = anIntArray1643[j1];
			int j2 = anIntArray1644[j1];
			int l2 = anIntArray1645[j1];
			Canvas3D.method378(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j],
					anIntArray1665[k], anIntArray1665[l], anIntArray1634[i], anIntArray1635[i], anIntArray1636[i], anIntArray1668[l1],
					anIntArray1668[j2], anIntArray1668[l2], anIntArray1669[l1], anIntArray1669[j2], anIntArray1669[l2],
					anIntArray1670[l1], anIntArray1670[j2], anIntArray1670[l2], anIntArray1640[i], vertexPerspectiveDepth[j], vertexPerspectiveDepth[k],
					vertexPerspectiveDepth[l]);
			return;
		}
		if (i1 == 3) {
			int k1 = anIntArray1637[i] >> 2;
			int i2 = anIntArray1643[k1];
			int k2 = anIntArray1644[k1];
			int i3 = anIntArray1645[k1];
			Canvas3D.method378(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j],
					anIntArray1665[k], anIntArray1665[l], anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[i2],
					anIntArray1668[k2], anIntArray1668[i3], anIntArray1669[i2], anIntArray1669[k2], anIntArray1669[i3],
					anIntArray1670[i2], anIntArray1670[k2], anIntArray1670[i3], anIntArray1640[i], vertexPerspectiveDepth[j], vertexPerspectiveDepth[k],
					vertexPerspectiveDepth[l]);
		}
	}

	private final void method485(int i) {
		if (anIntArray1640 != null)
			if (anIntArray1640[i] == 65535)
				return;
		int j = Canvas3D.centerX;
		int k = Canvas3D.centerY;
		int l = 0;
		int i1 = anIntArray1631[i];
		int j1 = anIntArray1632[i];
		int k1 = anIntArray1633[i];
		int l1 = anIntArray1670[i1];
		int i2 = anIntArray1670[j1];
		int j2 = anIntArray1670[k1];

		if (l1 >= 50) {
			anIntArray1678[l] = anIntArray1665[i1];
			anIntArray1679[l] = anIntArray1666[i1];
			anIntArray1680[l++] = anIntArray1634[i];
		} else {
			int k2 = anIntArray1668[i1];
			int k3 = anIntArray1669[i1];
			int k4 = anIntArray1634[i];
			if (j2 >= 50) {
				int k5 = (50 - l1) * modelIntArray4[j2 - l1];
				anIntArray1678[l] = j + (k2 + ((anIntArray1668[k1] - k2) * k5 >> 16) << Client.log_view_dist) / 50;
				anIntArray1679[l] = k + (k3 + ((anIntArray1669[k1] - k3) * k5 >> 16) << Client.log_view_dist) / 50;
				anIntArray1680[l++] = k4 + ((anIntArray1636[i] - k4) * k5 >> 16);
			}
			if (i2 >= 50) {
				int l5 = (50 - l1) * modelIntArray4[i2 - l1];
				anIntArray1678[l] = j + (k2 + ((anIntArray1668[j1] - k2) * l5 >> 16) << Client.log_view_dist) / 50;
				anIntArray1679[l] = k + (k3 + ((anIntArray1669[j1] - k3) * l5 >> 16) << Client.log_view_dist) / 50;
				anIntArray1680[l++] = k4 + ((anIntArray1635[i] - k4) * l5 >> 16);
			}
		}
		if (i2 >= 50) {
			anIntArray1678[l] = anIntArray1665[j1];
			anIntArray1679[l] = anIntArray1666[j1];
			anIntArray1680[l++] = anIntArray1635[i];
		} else {
			int l2 = anIntArray1668[j1];
			int l3 = anIntArray1669[j1];
			int l4 = anIntArray1635[i];
			if (l1 >= 50) {
				int i6 = (50 - i2) * modelIntArray4[l1 - i2];
				anIntArray1678[l] = j + (l2 + ((anIntArray1668[i1] - l2) * i6 >> 16) << Client.log_view_dist) / 50;
				anIntArray1679[l] = k + (l3 + ((anIntArray1669[i1] - l3) * i6 >> 16) << Client.log_view_dist) / 50;
				anIntArray1680[l++] = l4 + ((anIntArray1634[i] - l4) * i6 >> 16);
			}
			if (j2 >= 50) {
				int j6 = (50 - i2) * modelIntArray4[j2 - i2];
				anIntArray1678[l] = j + (l2 + ((anIntArray1668[k1] - l2) * j6 >> 16) << Client.log_view_dist) / 50;
				anIntArray1679[l] = k + (l3 + ((anIntArray1669[k1] - l3) * j6 >> 16) << Client.log_view_dist) / 50;
				anIntArray1680[l++] = l4 + ((anIntArray1636[i] - l4) * j6 >> 16);
			}
		}
		if (j2 >= 50) {
			anIntArray1678[l] = anIntArray1665[k1];
			anIntArray1679[l] = anIntArray1666[k1];
			anIntArray1680[l++] = anIntArray1636[i];
		} else {
			int i3 = anIntArray1668[k1];
			int i4 = anIntArray1669[k1];
			int i5 = anIntArray1636[i];
			if (i2 >= 50) {
				int k6 = (50 - j2) * modelIntArray4[i2 - j2];
				anIntArray1678[l] = j + (i3 + ((anIntArray1668[j1] - i3) * k6 >> 16) << Client.log_view_dist) / 50;
				anIntArray1679[l] = k + (i4 + ((anIntArray1669[j1] - i4) * k6 >> 16) << Client.log_view_dist) / 50;
				anIntArray1680[l++] = i5 + ((anIntArray1635[i] - i5) * k6 >> 16);
			}
			if (l1 >= 50) {
				int l6 = (50 - j2) * modelIntArray4[l1 - j2];
				anIntArray1678[l] = j + (i3 + ((anIntArray1668[i1] - i3) * l6 >> 16) << Client.log_view_dist) / 50;
				anIntArray1679[l] = k + (i4 + ((anIntArray1669[i1] - i4) * l6 >> 16) << Client.log_view_dist) / 50;
				anIntArray1680[l++] = i5 + ((anIntArray1634[i] - i5) * l6 >> 16);
			}
		}
		int j3 = anIntArray1678[0];
		int j4 = anIntArray1678[1];
		int j5 = anIntArray1678[2];
		int i7 = anIntArray1679[0];
		int j7 = anIntArray1679[1];
		int k7 = anIntArray1679[2];
		if ((j3 - j4) * (k7 - j7) - (i7 - j7) * (j5 - j4) > 0) {
			Canvas3D.restrict_edges = false;
			if (l == 3) {
				if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > Canvas2D.centerX || j4 > Canvas2D.centerX
						|| j5 > Canvas2D.centerX)
					Canvas3D.restrict_edges = true;
				int l7;
				if (anIntArray1637 == null)
					l7 = 0;
				else
					l7 = anIntArray1637[i] & 3;
				if (l7 == 0)
					Canvas3D.method374(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1],
							anIntArray1680[2], 0, 0, 0);
				else if (l7 == 1)
					Canvas3D.method376(i7, j7, k7, j3, j4, j5, modelIntArray3[anIntArray1634[i]], 0, 0, 0);
				else if (l7 == 2) {
					int j8 = anIntArray1637[i] >> 2;
					int k9 = anIntArray1643[j8];
					int k10 = anIntArray1644[j8];
					int k11 = anIntArray1645[j8];
					Canvas3D.method378(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1],
							anIntArray1680[2], anIntArray1668[k9], anIntArray1668[k10], anIntArray1668[k11],
							anIntArray1669[k9], anIntArray1669[k10], anIntArray1669[k11], anIntArray1670[k9],
							anIntArray1670[k10], anIntArray1670[k11], anIntArray1640[i], vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
				} else if (l7 == 3) {
					int k8 = anIntArray1637[i] >> 2;
					int l9 = anIntArray1643[k8];
					int l10 = anIntArray1644[k8];
					int l11 = anIntArray1645[k8];
					Canvas3D.method378(i7, j7, k7, j3, j4, j5, anIntArray1634[i], anIntArray1634[i],
							anIntArray1634[i], anIntArray1668[l9], anIntArray1668[l10], anIntArray1668[l11],
							anIntArray1669[l9], anIntArray1669[l10], anIntArray1669[l11], anIntArray1670[l9],
							anIntArray1670[l10], anIntArray1670[l11], anIntArray1640[i], vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
				}
			}
			if (l == 4) {
				if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > Canvas2D.centerX || j4 > Canvas2D.centerX
						|| j5 > Canvas2D.centerX || anIntArray1678[3] < 0 || anIntArray1678[3] > Canvas2D.centerX)
					Canvas3D.restrict_edges = true;
				int i8;
				if (anIntArray1637 == null)
					i8 = 0;
				else
					i8 = anIntArray1637[i] & 3;
				if (i8 == 0) {
					Canvas3D.method374(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1],
							anIntArray1680[2], 0, 0, 0);
					Canvas3D.method374(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0],
							anIntArray1680[2], anIntArray1680[3], vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
					return;
				}
				if (i8 == 1) {
					int l8 = modelIntArray3[anIntArray1634[i]];
					Canvas3D.method376(i7, j7, k7, j3, j4, j5, l8, 0, 0, 0);
					Canvas3D.method376(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], l8, vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
					return;
				}
				if (i8 == 2) {
					int i9 = anIntArray1637[i] >> 2;
					int i10 = anIntArray1643[i9];
					int i11 = anIntArray1644[i9];
					int i12 = anIntArray1645[i9];
					Canvas3D.method378(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1],
							anIntArray1680[2], anIntArray1668[i10], anIntArray1668[i11], anIntArray1668[i12],
							anIntArray1669[i10], anIntArray1669[i11], anIntArray1669[i12], anIntArray1670[i10],
							anIntArray1670[i11], anIntArray1670[i12], anIntArray1640[i], vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
					Canvas3D.method378(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0],
							anIntArray1680[2], anIntArray1680[3], anIntArray1668[i10], anIntArray1668[i11],
							anIntArray1668[i12], anIntArray1669[i10], anIntArray1669[i11], anIntArray1669[i12],
							anIntArray1670[i10], anIntArray1670[i11], anIntArray1670[i12], anIntArray1640[i], vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
					return;
				}
				if (i8 == 3) {
					int j9 = anIntArray1637[i] >> 2;
					int j10 = anIntArray1643[j9];
					int j11 = anIntArray1644[j9];
					int j12 = anIntArray1645[j9];
					Canvas3D.method378(i7, j7, k7, j3, j4, j5, anIntArray1634[i], anIntArray1634[i],
							anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12],
							anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10],
							anIntArray1670[j11], anIntArray1670[j12], anIntArray1640[i], vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
					Canvas3D.method378(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1634[i],
							anIntArray1634[i], anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11],
							anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12],
							anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], anIntArray1640[i], vertexPerspectiveDepth[i1], vertexPerspectiveDepth[j1], vertexPerspectiveDepth[k1]);
				}
			}
		}
	}

	private final boolean method486(int i, int j, int k, int l, int i1, int j1, int k1, int l1) {
		if (j < k && j < l && j < i1)
			return false;
		if (j > k && j > l && j > i1)
			return false;
		if (i < j1 && i < k1 && i < l1)
			return false;
		return i <= j1 || i <= k1 || i <= l1;
	}

	private byte[] texture_coordinates;
	public static boolean aBoolean1684;
	private static boolean aBooleanArray1663[] = new boolean[8000];
	private static boolean aBooleanArray1664[] = new boolean[8000];
	private static ModelHeader aClass21Array1661[];
	public static Model aModel_1621 = new Model(true);
	private static int anInt1681;
	private static int anInt1682;
	private static int anInt1683;
	public static int anInt1685;
	public static int anInt1686;
	public static int anInt1687;
	public int anInt1626;
	public int anIntArray1627[];
	public int anIntArray1628[];
	public int anIntArray1629[];
	public int anInt1630;
	public int anIntArray1631[];
	public int anIntArray1632[];
	public int anIntArray1633[];
	public int anIntArray1634[];
	public int anIntArray1635[];
	public int anIntArray1636[];
	public int anIntArray1637[];
	public int anIntArray1638[];
	public int anIntArray1639[];
	public int anIntArray1640[];
	public int anInt1641;
	public int anInt1642;
	public int anIntArray1643[];
	public int anIntArray1644[];
	public int anIntArray1645[];
	public int anInt1646;
	public int anInt1647;
	public int anInt1648;
	public int anInt1649;
	public int anInt1650;
	public int anInt1651;
	public int anInt1652;
	public int anInt1653;
	public int anInt1654;
	public int anIntArray1655[];
	public int anIntArray1656[];
	public int anIntArrayArray1657[][];
	public int anIntArrayArray1658[][];
	public boolean aBoolean1659;
	public Class33 aClass33Array1660[];
	private boolean aBoolean1618;
	public static int anInt1620;
	private static int anIntArray1622[] = new int[2000];
	private static int anIntArray1623[] = new int[2000];
	private static int anIntArray1624[] = new int[2000];
	private static int anIntArray1625[] = new int[2000];
	private static int vertexPerspectiveDepth[] = new int[8000];
	private static int anIntArray1665[] = new int[8000];
	private static int anIntArray1666[] = new int[8000];
	private static int anIntArray1667[] = new int[8000];
	private static int anIntArray1668[] = new int[8000];
	private static int anIntArray1669[] = new int[8000];
	private static int anIntArray1670[] = new int[8000];
	private static int anIntArray1671[] = new int[1500];
	private static int anIntArray1673[] = new int[12];
	private static int anIntArray1675[] = new int[2000];
	private static int anIntArray1676[] = new int[2000];
	private static int anIntArray1677[] = new int[12];
	private static int anIntArray1678[] = new int[10];
	private static int anIntArray1679[] = new int[10];
	private static int anIntArray1680[] = new int[10];
	public static int mapObjIds[] = new int[1000];
	public static int anIntArray1688[] = new int[1000];
	private static int anIntArrayArray1672[][] = new int[1500][512];
	private static int anIntArrayArray1674[][] = new int[12][2000];
	private static CacheFileRequester aOnDemandFetcherParent_1662;
	public static int modelIntArray1[];
	public static int modelIntArray2[];
	private static int[] modelIntArray3;
	private static int[] modelIntArray4;
	public static boolean fog;

	static {
		modelIntArray1 = Canvas3D.SINE;
		modelIntArray2 = Canvas3D.COSINE;
		modelIntArray3 = Canvas3D.anIntArray1482;
		modelIntArray4 = Canvas3D.anIntArray1469;
	}
}
