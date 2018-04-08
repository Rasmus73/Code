using System;

namespace Triangle
{
    public class Line
    {
        public int Length { get; }
        public Line(int length)
        {
            if (length < 0 ) throw new ArgumentException("Line length cant be negative");
            Length = length;
        }
    }
}