package androidx.constraintlayout.solver.widgets;

public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f18x;

    /* renamed from: y */
    public int f19y;

    public void setBounds(int x, int y, int width2, int height2) {
        this.f18x = x;
        this.f19y = y;
        this.width = width2;
        this.height = height2;
    }

    /* access modifiers changed from: 0000 */
    public void grow(int w, int h) {
        this.f18x -= w;
        this.f19y -= h;
        this.width += w * 2;
        this.height += h * 2;
    }

    /* access modifiers changed from: 0000 */
    public boolean intersects(Rectangle bounds) {
        int i = this.f18x;
        int i2 = bounds.f18x;
        if (i >= i2 && i < i2 + bounds.width) {
            int i3 = this.f19y;
            int i4 = bounds.f19y;
            if (i3 >= i4 && i3 < i4 + bounds.height) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(int x, int y) {
        int i = this.f18x;
        if (x >= i && x < i + this.width) {
            int i2 = this.f19y;
            if (y >= i2 && y < i2 + this.height) {
                return true;
            }
        }
        return false;
    }

    public int getCenterX() {
        return (this.f18x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f19y + this.height) / 2;
    }
}
