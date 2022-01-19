#version 150

#moj_import <matrix.glsl>

uniform sampler2D Sampler0;
uniform vec2 ScreenSize;
uniform float GameTime;

in vec4 texProj0;
out vec4 fragColor;

void main() {
	// vec2 p = gl_FragCoord.xy / ScreenSize;
	fragColor = textureProj(Sampler0, texProj0);
}