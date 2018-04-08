using System.Collections.Generic;

namespace Triangle
{
    public class Triangle : Shape
    {
        public Triangle(IReadOnlyList<Line> lines) : base(lines, new TriangleGeometryResolver())
        {
        }
    }
}