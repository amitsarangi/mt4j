package org.mt4j.util.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL;

public class JoglWrapper11 extends JoglWrapper10 implements GL11 {
	private GL gl;

	public JoglWrapper11(GL gl) {
		super(gl);
		this.gl = gl;
	}

	@Override 
	public void glBindBuffer (int target, int buffer) {
		gl.glBindBuffer(target, buffer);
	}

	@Override 
	public void glBufferData (int target, int size, Buffer data, int usage) {
		gl.glBufferData(target, size, data, usage);
	}

	@Override 
	public void glBufferSubData (int target, int offset, int size, Buffer data) {
		gl.glBufferSubData(target, offset, size, data);
	}

	@Override 
	public void glClipPlanef (int plane, float[] equation, int offset) {
		throw new UnsupportedOperationException();
	}

	@Override 
	public void glClipPlanef (int plane, FloatBuffer equation) {
		throw new UnsupportedOperationException();
	}

	@Override 
	public void glColor4ub (byte red, byte green, byte blue, byte alpha) {
		throw new UnsupportedOperationException();
	}

	@Override 
	public void glDeleteBuffers (int n, int[] buffers, int offset) {
		gl.glDeleteBuffers(n, buffers, offset);
	}

	@Override 
	public void glDeleteBuffers (int n, IntBuffer buffers) {
		gl.glDeleteBuffers(n, buffers);
	}

	@Override 
	public void glGenBuffers (int n, int[] buffers, int offset) {
		gl.glGenBuffers(n, buffers, offset);
	}

	@Override 
	public void glGenBuffers (int n, IntBuffer buffers) {
		gl.glGenBuffers(n, buffers);
	}

	@Override 
	public void glGetBooleanv (int pname, boolean[] params, int offset) {
		throw new UnsupportedOperationException();
	}

	@Override 
	public void glGetBooleanv (int pname, IntBuffer params) {
		throw new UnsupportedOperationException();
	}

	@Override 
	public void glGetBufferParameteriv (int target, int pname, int[] params, int offset) {
		gl.glGetBufferParameteriv(target, pname, params, offset);
	}

	@Override 
	public void glGetBufferParameteriv (int target, int pname, IntBuffer params) {
		gl.glGetBufferParameteriv(target, pname, params);
	}

	@Override 
	public void glGetClipPlanef (int pname, float[] eqn, int offset) {
		throw new UnsupportedOperationException();
	}

	@Override 
	public void glGetClipPlanef (int pname, FloatBuffer eqn) {
		throw new UnsupportedOperationException();
	}

	@Override 
	public void glGetFloatv (int pname, float[] params, int offset) {
		gl.glGetFloatv(pname, params, offset);
	}

	@Override 
	public void glGetFloatv (int pname, FloatBuffer params) {
		gl.glGetFloatv(pname, params);
	}

	@Override 
	public void glGetLightfv (int light, int pname, float[] params, int offset) {
		gl.glGetLightfv(light, pname, params, offset);
	}

	@Override 
	public void glGetLightfv (int light, int pname, FloatBuffer params) {
		gl.glGetLightfv(light, pname, params);
	}

	@Override 
	public void glGetMaterialfv (int face, int pname, float[] params, int offset) {
		gl.glGetMaterialfv(face, pname, params, offset);
	}

	@Override 
	public void glGetMaterialfv (int face, int pname, FloatBuffer params) {
		gl.glGetMaterialfv(face, pname, params);
	}

	@Override 
	public void glGetPointerv (int pname, Buffer[] params) {
		throw new UnsupportedOperationException();
	}

	@Override 
	public void glGetTexEnviv (int env, int pname, int[] params, int offset) {
		gl.glGetTexEnviv(env, pname, params, offset);
	}

	@Override 
	public void glGetTexEnviv (int env, int pname, IntBuffer params) {
		gl.glGetTexEnviv(env, pname, params);
	}

	@Override 
	public void glGetTexParameterfv (int target, int pname, float[] params, int offset) {
		gl.glGetTexParameterfv(target, pname, params, offset);
	}

	@Override 
	public void glGetTexParameterfv (int target, int pname, FloatBuffer params) {
		gl.glGetTexParameterfv(target, pname, params);
	}

	@Override 
	public void glGetTexParameteriv (int target, int pname, int[] params, int offset) {
		gl.glGetTexParameteriv(target, pname, params, offset);
	}

	@Override 
	public void glGetTexParameteriv (int target, int pname, IntBuffer params) {
		gl.glGetTexParameteriv(target, pname, params);
	}

	@Override 
	public boolean glIsBuffer (int buffer) {
		return gl.glIsBuffer(buffer);
	}

	@Override 
	public boolean glIsEnabled (int cap) {
		return gl.glIsEnabled(cap);
	}

	@Override
	public boolean glIsTexture (int texture) {
		return gl.glIsTexture(texture);
	}

	@Override 
	public void glPointParameterf (int pname, float param) {
		gl.glPointParameterf(pname, param);
	}

	@Override 
	public void glPointParameterfv (int pname, float[] params, int offset) {
		gl.glPointParameterfv(pname, params, offset);
	}

	@Override 
	public void glPointParameterfv (int pname, FloatBuffer params) {
		gl.glPointParameterfv(pname, params);
	}

	@Override 
	public void glPointSizePointerOES (int type, int stride, Buffer pointer) {
		throw new UnsupportedOperationException();
	}

	@Override 
	public void glTexEnvi (int target, int pname, int param) {
		gl.glTexEnvi(target, pname, param);
	}

	@Override 
	public void glTexEnviv (int target, int pname, int[] params, int offset) {
		gl.glTexEnviv(target, pname, params, offset);
	}

	@Override 
	public void glTexEnviv (int target, int pname, IntBuffer params) {
		gl.glTexEnviv(target, pname, params);
	}

	@Override 
	public void glTexParameterfv (int target, int pname, float[] params, int offset) {
		gl.glTexParameterfv(target, pname, params, offset);
	}

	@Override 
	public void glTexParameterfv (int target, int pname, FloatBuffer params) {
		gl.glTexParameterfv(target, pname, params);
	}

	@Override 
	public void glTexParameteri (int target, int pname, int param) {
		gl.glTexParameteri(target, pname, param);
	}

	@Override 
	public void glTexParameteriv (int target, int pname, int[] params, int offset) {
		gl.glTexParameteriv(target, pname, params, offset);
	}

	@Override 
	public void glTexParameteriv (int target, int pname, IntBuffer params) {
		gl.glTexParameteriv(target, pname, params);
	}

	@Override 
	public void glColorPointer (int size, int type, int stride, int pointer) {
		gl.glColorPointer(size, type, stride, pointer);
	}

	@Override 
	public void glNormalPointer (int type, int stride, int pointer) {
		gl.glNormalPointer(type, stride, pointer);
	}

	@Override 
	public void glTexCoordPointer (int size, int type, int stride, int pointer) {
		gl.glTexCoordPointer(size, type, stride, pointer);
	}

	@Override 
	public void glVertexPointer (int size, int type, int stride, int pointer) {
		gl.glVertexPointer(size, type, stride, pointer);
	}

	@Override 
	public void glDrawElements (int mode, int count, int type, int indices) {
		gl.glDrawElements(mode, count, type, indices);
	}

	
//	public Object getWrappedImplementation(){
//		return this.gl;
//	}
//
//	@Override
//	public void glAlphaFunc(int func, float ref) {
//		gl.glAlphaFunc(func, ref);
//	}
//
//	@Override
//	public void glClientActiveTexture(int texture) {
//		gl.glClientActiveTexture(texture);
//	}
//
//	@Override
//	public void glColor4f(float red, float green, float blue, float alpha) {
//		gl.glColor4f(red, green, blue, alpha);
//	}
//
//	@Override
//	public void glColorPointer(int size, int type, int stride, Buffer pointer) {
//		gl.glColorPointer(size, type, stride, pointer);
//	}
//
//	@Override
//	public void glDeleteTextures(int n, int[] textures, int offset) {
//		gl.glDeleteTextures(n, textures, offset);
//	}
//
//	@Override
//	public void glDisableClientState(int array) {
//		gl.glDisableClientState(array);
//	}
//
//	@Override
//	public void glEnableClientState(int array) {
//		gl.glEnableClientState(array);
//	}
//
//	@Override
//	public void glFogf(int pname, float param) {
//		gl.glFogf(pname, param);
//	}
//
//	@Override
//	public void glFogfv(int pname, float[] params, int offset) {
//		gl.glFogfv(pname, params, offset);
//	}
//
//	@Override
//	public void glFogfv(int pname, FloatBuffer params) {
//		gl.glFogfv(pname, params);
//	}
//
//	@Override
//	public void glFrustumf(float left, float right, float bottom, float top,
//			float zNear, float zFar) {
//		gl.glFrustum(left, right, bottom, top, zNear, zFar);
//	}
//
//	@Override
//	public void glGenTextures(int n, int[] textures, int offset) {
//		gl.glGenTextures(n, textures, offset);
//	}
//
//	@Override
//	public void glGetIntegerv(int pname, int[] params, int offset) {
//		gl.glGetIntegerv(pname, params, offset);
//	}
//
//	@Override
//	public void glLightModelf(int pname, float param) {
//		gl.glLightModelf(pname, param);
//	}
//
//	@Override
//	public void glLightModelfv(int pname, float[] params, int offset) {
//		gl.glLightModelfv(pname, params, offset);
//	}
//
//	@Override
//	public void glLightModelfv(int pname, FloatBuffer params) {
//		gl.glLightModelfv(pname, params);
//	}
//
//	@Override
//	public void glLightf(int light, int pname, float param) {
//		gl.glLightf(light, pname, param);
//	}
//
//	@Override
//	public void glLightfv(int light, int pname, float[] params, int offset) {
//		gl.glLightfv(light, pname, params, offset);
//	}
//
//	@Override
//	public void glLightfv(int light, int pname, FloatBuffer params) {
//		gl.glLightfv(light, pname, params);
//	}
//
//	@Override
//	public void glLoadIdentity() {
//		gl.glLoadIdentity();
//	}
//
//	@Override
//	public void glLoadMatrixf(float[] m, int offset) {
//		gl.glLoadMatrixf(m, offset);
//	}
//
//	@Override
//	public void glLoadMatrixf(FloatBuffer m) {
//		gl.glLoadMatrixf(m);
//	}
//
//	@Override
//	public void glLogicOp(int opcode) {
//		gl.glLogicOp(opcode);
//	}
//
//	@Override
//	public void glMaterialf(int face, int pname, float param) {
//		gl.glMaterialf(face, pname, param);
//	}
//
//	@Override
//	public void glMaterialfv(int face, int pname, float[] params, int offset) {
//		gl.glMaterialfv(face, pname, params, offset);
//	}
//
//	@Override
//	public void glMaterialfv(int face, int pname, FloatBuffer params) {
//		gl.glMaterialfv(face, pname, params);
//	}
//
//	@Override
//	public void glMatrixMode(int mode) {
//		gl.glMatrixMode(mode);
//	}
//
//	@Override
//	public void glMultMatrixf(float[] m, int offset) {
//		gl.glMultMatrixf(m, offset);
//	}
//
//	@Override
//	public void glMultMatrixf(FloatBuffer m) {
//		gl.glMultMatrixf(m);
//	}
//
//	@Override
//	public void glMultiTexCoord4f(int target, float s, float t, float r, float q) {
//		gl.glMultiTexCoord4f(target, s, t, r, q);
//	}
//
//	@Override
//	public void glNormal3f(float nx, float ny, float nz) {
//		gl.glNormal3f(nx, ny, nz);
//	}
//
//	@Override
//	public void glNormalPointer(int type, int stride, Buffer pointer) {
//		gl.glNormalPointer(type, stride, pointer);
//	}
//
//	@Override
//	public void glOrthof(float left, float right, float bottom, float top,
//			float zNear, float zFar) {
//		gl.glOrtho(left, right, bottom, top, zNear, zFar);
//	}
//
//	@Override
//	public void glPointSize(float size) {
//		gl.glPointSize(size);
//	}
//
//	@Override
//	public void glPopMatrix() {
//		gl.glPopMatrix();
//	}
//
//	@Override
//	public void glPushMatrix() {
//		gl.glPushMatrix();
//	}
//
//	@Override
//	public void glRotatef(float angle, float x, float y, float z) {
//		gl.glRotatef(angle, x, y, z);
//	}
//
//	@Override
//	public void glSampleCoverage(float value, boolean invert) {
//		gl.glSampleCoverage(value, invert);
//	}
//
//	@Override
//	public void glScalef(float x, float y, float z) {
//		gl.glScalef(x, y, z);
//	}
//
//	@Override
//	public void glShadeModel(int mode) {
//		gl.glShadeModel(mode);
//	}
//
//	@Override
//	public void glTexCoordPointer(int size, int type, int stride, Buffer pointer) {
//		gl.glTexCoordPointer(size, type, stride, pointer);
//	}
//
//	@Override
//	public void glTexEnvf(int target, int pname, float param) {
//		gl.glTexEnvf(target, pname, param);
//	}
//
//	@Override
//	public void glTexEnvfv(int target, int pname, float[] params, int offset) {
//		gl.glTexEnvfv(target, pname, params, offset);
//	}
//
//	@Override
//	public void glTexEnvfv(int target, int pname, FloatBuffer params) {
//		gl.glTexEnvfv(target, pname, params);
//	}
//
//	@Override
//	public void glTranslatef(float x, float y, float z) {
//		gl.glTranslatef(x, y, z);
//	}
//
//	@Override
//	public void glVertexPointer(int size, int type, int stride, Buffer pointer) {
//		gl.glVertexPointer(size, type, stride, pointer);
//	}
//
//	@Override
//	public void glPolygonMode(int face, int mode) {
//		gl.glPolygonMode(face, mode);
//	}
//
//	@Override
//	public void glActiveTexture(int texture) {
//		gl.glActiveTexture(texture);
//	}
//
//	@Override
//	public void glBindTexture(int target, int texture) {
//		gl.glBindTexture(target, texture);
//	}
//
//	@Override
//	public void glBlendFunc(int sfactor, int dfactor) {
//		gl.glBlendFunc(sfactor, dfactor);
//	}
//
//	@Override
//	public void glClear(int mask) {
//		gl.glClear(mask);
//	}
//
//	@Override
//	public void glClearColor(float red, float green, float blue, float alpha) {
//		gl.glClearColor(red, green, blue, alpha);
//	}
//
//	@Override
//	public void glClearDepthf(float depth) {
//		gl.glClearDepth(depth);
//	}
//
//	@Override
//	public void glClearStencil(int s) {
//		gl.glClearStencil(s);
//	}
//
//	@Override
//	public void glColorMask(boolean red, boolean green, boolean blue,
//			boolean alpha) {
//		gl.glColorMask(red, green, blue, alpha);
//	}
//
//	@Override
//	public void glCompressedTexImage2D(int target, int level,
//			int internalformat, int width, int height, int border,
//			int imageSize, Buffer data) {
//		gl.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
//	}
//
//	@Override
//	public void glCompressedTexSubImage2D(int target, int level, int xoffset,
//			int yoffset, int width, int height, int format, int imageSize,
//			Buffer data) {
//		gl.glCompressedTexSubImage2D( target,  level,  xoffset,
//		 yoffset,  width,  height,  format,  imageSize,
//		 data);
//	}
//
//	@Override
//	public void glCopyTexImage2D(int target, int level, int internalformat,
//			int x, int y, int width, int height, int border) {
//		gl.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
//	}
//
//	@Override
//	public void glCopyTexSubImage2D(int target, int level, int xoffset,
//			int yoffset, int x, int y, int width, int height) {
//		gl.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
//	}
//
//	@Override
//	public void glCullFace(int mode) {
//		gl.glCullFace(mode);
//	}
//
//	@Override
//	public void glDeleteTextures(int n, IntBuffer textures) {
//		gl.glDeleteTextures(n, textures);
//	}
//
//	@Override
//	public void glDepthFunc(int func) {
//		gl.glDepthFunc(func);
//	}
//
//	@Override
//	public void glDepthMask(boolean flag) {
//		gl.glDepthMask(flag);
//	}
//
//	@Override
//	public void glDepthRangef(float zNear, float zFar) {
//		gl.glDepthRange(zNear, zNear);
//	}
//
//	@Override
//	public void glDisable(int cap) {
//		gl.glDisable(cap);
//	}
//
//	@Override
//	public void glDrawArrays(int mode, int first, int count) {
//		gl.glDrawArrays(mode, first, count);
//	}
//
//	@Override
//	public void glDrawElements(int mode, int count, int type, Buffer indices) {
//		gl.glDrawElements(mode, count, type, indices);
//	}
//
//	@Override
//	public void glEnable(int cap) {
//		gl.glEnable(cap);
//	}
//
//	@Override
//	public void glFinish() {
//		gl.glFinish();
//	}
//
//	@Override
//	public void glFlush() {
//		gl.glFlush();
//	}
//
//	@Override
//	public void glFrontFace(int mode) {
//		gl.glFrontFace(mode);
//	}
//
//	@Override
//	public void glGenTextures(int n, IntBuffer textures) {
//		gl.glGenTextures(n, textures);
//	}
//
//	@Override
//	public int glGetError() {
//		return gl.glGetError();
//	}
//
//	@Override
//	public void glGetIntegerv(int pname, IntBuffer params) {
//		gl.glGetIntegerv(pname, params);
//	}
//
//	@Override
//	public String glGetString(int name) {
//		return gl.glGetString(name);
//	}
//
//	@Override
//	public void glHint(int target, int mode) {
//		gl.glHint(target, mode);
//	}
//
//	@Override
//	public void glLineWidth(float width) {
//		gl.glLineWidth(width);
//	}
//
//	@Override
//	public void glPixelStorei(int pname, int param) {
//		gl.glPixelStorei(pname, param);
//	}
//
//	@Override
//	public void glPolygonOffset(float factor, float units) {
//		gl.glPolygonOffset(factor, units);
//	}
//
//	@Override
//	public void glReadPixels(int x, int y, int width, int height, int format,
//			int type, Buffer pixels) {
//		gl.glReadPixels(x, y, width, height, format, type, pixels);
//	}
//
//	@Override
//	public void glScissor(int x, int y, int width, int height) {
//		gl.glScissor(x, y, width, height);
//	}
//
//	@Override
//	public void glStencilFunc(int func, int ref, int mask) {
//		gl.glStencilFunc(func, ref, mask);
//	}
//
//	@Override
//	public void glStencilMask(int mask) {
//		gl.glStencilMask(mask);
//	}
//
//	@Override
//	public void glStencilOp(int fail, int zfail, int zpass) {
//		gl.glStencilOp(fail, zfail, zpass);
//	}
//
//	@Override
//	public void glTexImage2D(int target, int level, int internalformat,
//			int width, int height, int border, int format, int type,
//			Buffer pixels) {
//		gl.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
//	}
//
//	@Override
//	public void glTexParameterf(int target, int pname, float param) {
//		gl.glTexParameterf(target, pname, param);
//	}
//
//	@Override
//	public void glTexSubImage2D(int target, int level, int xoffset,
//			int yoffset, int width, int height, int format, int type,
//			Buffer pixels) {
//		gl.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
//	}
//
//	@Override
//	public void glViewport(int x, int y, int width, int height) {
//		gl.glViewport(x, y, width, height);
//	}
//
//	@Override
//	public void glClipPlanef(int plane, float[] equation, int offset) {
////		gl.glClipPlane(plane, equation , offset);
//		 throw new UnsupportedOperationException("not implemented");
//	}
//
//	@Override
//	public void glClipPlanef(int plane, FloatBuffer equation) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetClipPlanef(int pname, float[] eqn, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetClipPlanef(int pname, FloatBuffer eqn) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetFloatv(int pname, float[] params, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetFloatv(int pname, FloatBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetLightfv(int light, int pname, float[] params, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetLightfv(int light, int pname, FloatBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetMaterialfv(int face, int pname, float[] params, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetMaterialfv(int face, int pname, FloatBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetTexParameterfv(int target, int pname, float[] params,
//			int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetTexParameterfv(int target, int pname, FloatBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glPointParameterf(int pname, float param) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glPointParameterfv(int pname, float[] params, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glPointParameterfv(int pname, FloatBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glTexParameterfv(int target, int pname, float[] params,
//			int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glTexParameterfv(int target, int pname, FloatBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glBindBuffer(int target, int buffer) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glBufferData(int target, int size, Buffer data, int usage) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glBufferSubData(int target, int offset, int size, Buffer data) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glColor4ub(byte red, byte green, byte blue, byte alpha) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glDeleteBuffers(int n, int[] buffers, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glDeleteBuffers(int n, IntBuffer buffers) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetBooleanv(int pname, boolean[] params, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetBooleanv(int pname, IntBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetBufferParameteriv(int target, int pname, int[] params,
//			int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetBufferParameteriv(int target, int pname, IntBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGenBuffers(int n, int[] buffers, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGenBuffers(int n, IntBuffer buffers) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetPointerv(int pname, Buffer[] params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetTexEnviv(int env, int pname, int[] params, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetTexEnviv(int env, int pname, IntBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetTexParameteriv(int target, int pname, int[] params,
//			int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glGetTexParameteriv(int target, int pname, IntBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean glIsBuffer(int buffer) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean glIsEnabled(int cap) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean glIsTexture(int texture) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void glTexEnvi(int target, int pname, int param) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glTexEnviv(int target, int pname, int[] params, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glTexEnviv(int target, int pname, IntBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glTexParameteri(int target, int pname, int param) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glTexParameteriv(int target, int pname, int[] params, int offset) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glTexParameteriv(int target, int pname, IntBuffer params) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glPointSizePointerOES(int type, int stride, Buffer pointer) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glVertexPointer(int size, int type, int stride, int pointer) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glColorPointer(int size, int type, int stride, int pointer) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glNormalPointer(int type, int stride, int pointer) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glTexCoordPointer(int size, int type, int stride, int pointer) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void glDrawElements(int mode, int count, int type, int indices) {
//		// TODO Auto-generated method stub
//		
//	}

}
