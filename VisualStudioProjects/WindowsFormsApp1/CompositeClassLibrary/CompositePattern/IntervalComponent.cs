using Model.ValueTypes;
using System;
using System.Drawing;

namespace CompositeClassLibrary.CompositePattern
{
    public abstract class IntervalComponent : IIntervalComponent
    {
        public IntervalType Interval { get; }

        private string _name;

        public IntervalComponent(string name, IntervalType interval)
        {
            if (interval == null) throw new ArgumentNullException(nameof(interval));

            Interval = interval;
            _name = name;
        }

        public void Draw(Graphics graphics)
        {

            long y = Interval.Y;
            Pen myPen;
            myPen = new Pen(Color.Black);
            graphics.DrawLine(myPen, Interval.XStart, y, Interval.XEnd, y);

            graphics.DrawLine(myPen, Interval.XStart, y - 5, Interval.XStart, y + 5);
            graphics.DrawLine(myPen, Interval.XEnd, y - 5, Interval.XEnd, y + 5);


            Font font = new Font("TimesNewRoman", 8);
            //graphics.DrawString(_name, font, new SolidBrush(Color.Black), Interval.XStart + ((Interval.XEnd - Interval.XStart) / 2), y);
            graphics.DrawString(Interval.Comment, font, new SolidBrush(Color.Black), Interval.XStart, y);

            graphics.DrawString(Interval.StartDateTime.ToString(), font, new SolidBrush(Color.Black), Interval.XStart, y - 20);
            graphics.DrawString(Interval.EndDateTime.ToString(), font, new SolidBrush(Color.Black), Interval.XEnd, y - 20);

        }
    }
}