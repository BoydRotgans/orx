#version 330 core

uniform sampler2D tex0;
in vec2 v_texCoord0;

out vec4 o_color;

void main() {
    vec2 size = textureSize(tex0, 0);
    vec2 pixelPosition = v_texCoord0;
    vec2 centroidPixelPosition = texture(tex0, v_texCoord0).xy;
    vec2 pixelDistance = (centroidPixelPosition - pixelPosition) * size * vec2(1.0, -1.0);

    o_color = vec4(pixelDistance, 0.0, 1.0);
}