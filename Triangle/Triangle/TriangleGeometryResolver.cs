using System.Collections.Generic;
using Triangle.Interface;

namespace Triangle
{
    public class TriangleGeometryResolver : IGeometryResolver
    {
        public GeometryType GetGeometryType(IReadOnlyList<Line> lines)
        {
            if (lines.Count != 3)
            {
                return GeometryType.UnknownShape;
            }

            //if all lines are equal then its a rectangular triangle
            if (lines[0].Length == lines[1].Length && lines[1].Length == lines[2].Length)
            {
                return GeometryType.EquilateralTriangle;
            }

            //if 2 lines are equal then its an isosceles triangle
            if (lines[0].Length == lines[1].Length || lines[0].Length == lines[2].Length || lines[1].Length == lines[2].Length)
            {
                return GeometryType.IsoscelesTriangle;
            }
            
            return GeometryType.UnknownShape;
        }
    }
}